<!DOCTYPE HTML>
<html>

<head>
    <title>OpenCaption</title>

    <script type="text/javascript">
        function resize_canvas(){
            canvas = document.getElementById("transcript");
            if (canvas.width  < window.innerWidth)
            {
                canvas.width  = window.innerWidth - 35;
            }

            if (canvas.height < window.innerHeight)
            {
                canvas.height = window.innerHeight;
            }
        }
		
		function timedRefresh(timeoutPeriod) {
			setTimeout("location.reload(true);",timeoutPeriod);
		}
    </script>
	
	<style>
	header left {
		float:left;
		margin-left:auto;
		margin-right:auto;
	}
	</style>
</head>

<header id="left">
    <img src="OpenCaption_title.jpg" alt="OpenCaption Logo" />
	<!-- <a href="transcript.txt" download="transcript.txt"><img src="DownloadCurrentCaption.jpg" alt="DownloadCurrentCaption Logo" style="float: right"/></a> -->
	<a href="transcript.txt" download="transcript"><img src="DownloadCurrentCaption.jpg" alt="DownloadCurrentCaption Logo" style="float: right" /></a>
</header>

<body onload="JavaScript:timedRefresh(5000);">

<?php

$con = mysql_connect("localhost", "root", "opencaption");
$open = false;

if (!$con)
{
	die('Could not connect: ' . mysql_error());
}

mysql_select_db("opencaption", $con);

$result = mysql_query("SELECT * FROM demo ORDER BY id");

$filename = "transcript.txt";
$file = fopen( $filename, "w" );

$transcript = "";

while($row = mysql_fetch_array($result))
{
	echo $row['string']." ";
	$transcript .= $row['string']." ";
}

if( $file == true )
{
   fwrite( $file, $transcript);
}

fclose($file);

mysql_close($con);

?>

</body>

</html>