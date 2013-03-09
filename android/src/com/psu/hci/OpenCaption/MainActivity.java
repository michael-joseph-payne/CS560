package com.psu.hci.OpenCaption;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private int SPEECH_REQUEST_CODE = 1234;
    static boolean recording = false;
    static final String tag = "Open_Caption";

    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);

        final ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackground(getResources().getDrawable(R.drawable.micnotrecording));


        final SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Log.d(tag, "onReadyForSpeech");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.d(tag, "onBeginningOfSpeech");
            }

            @Override
            public void onRmsChanged(float rmsdB) {
//                Log.d(tag, "onRmsChanged");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                Log.d(tag, "onBufferReceived");
            }

            @Override
            public void onEndOfSpeech() {
                Log.d(tag, "onEndOfSpeech");
                sr.stopListening();
            }

            @Override
            public void onError(int error) {
                Log.d(tag, "onError" + error);
            }

            @Override
            public void onResults(Bundle b) {
                ArrayList<String> results = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Log.d(tag, "onResults = " + results.get(0));
                sr.stopListening();
                sr.startListening(intent);
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                ArrayList<String> results = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Log.d(tag, "onResults = " + results.get(0));
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                Log.d(tag, "onEvent");
            }

            // other required methods
        });




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recording) {
                    recording = false;
                    sr.stopListening();
                    button.setBackground(getResources().getDrawable(R.drawable.micnotrecording));
                } else {
                    recording = true;
                    sr.startListening(intent);
                    button.setBackground(getResources().getDrawable(R.drawable.micrecording));
                }
            }
        });
//
////        // Disable button if no recognition service is present
//        PackageManager pm = getPackageManager();
//        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
//
//        if (activities.size() == 0) {
//            speakButton.setEnabled(false);
//            speakButton.setText("Recognizer not present");
//        }
    }

    private void sendRecognizeIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the magic word");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            Toast.makeText(getApplicationContext(), "Results = " + matches, Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

