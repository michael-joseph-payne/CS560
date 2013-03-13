<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>OpenCaption</title>

<script type = "text/javascript">

function timedRefresh(timeoutPeriod)
{
	setTimeout("location.reload(true);",timeoutPeriod);
}
</script>

</head>

<?php

$con = mysql_connect("localhost", "root", "opencaption");

if (!$con)
{
	die('Could not connect: ' . mysql_error());
}

mysql_select_db("demo", $con);

$result = mysql_query("SELECT * FROM strings ORDER BY id");


echo "Hi\r\n";

while($row = mysql_fetch_array($result))
{
	echo $row['string']." ";
}

mysql_close($con);

?>

<body onload="JavaScript:timedRefresh(5000);">
</body>

</html>
