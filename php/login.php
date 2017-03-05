<?php

require "conn.php";
$email = $_POST["email"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from PlayerInfo where email like '$email' and password like '$user_pass';";
$result = mysqli_query($conn, $mysql_qry);
if (mysqli_num_rows($result) > 0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["FirstName"];
	$ID = $row["ID"];
	$value = $row["PlayerValue"];
	echo $name . " " . $ID . " " . $value;
}
else {
	echo "-1";
}

$conn->close();
?>