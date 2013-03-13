<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Untitled 1</title>
</head>

<?php

$string = $_GET['string'];

$con = mysql_connect("localhost","root","opencaption");

mysql_select_db("demo", $con);

$sql ="INSERT INTO strings (string) ";
$sql.="VALUES ('$string')";

if (!mysql_query($sql,$con))
{
	die('Error: ' . mysql_error());
}

echo "1 string added: ";
echo $string;
echo "\n";

mysql_close($con);

?>

<body>
</body>

</html>
