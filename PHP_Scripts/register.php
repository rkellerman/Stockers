<?php

require "conn.php";

$name = $_POST["name"];
$surname = $_POST["surname"];
$email = $_POST["email"];
$password = $_POST["password"];

$mysql_qry = "insert into PlayerInfo (FirstName, LastName, Email, Password, PlayerValue) values ('$name', '$surname', '$email', '$password', 10000);";

if ($conn->query($mysql_qry) === TRUE){
	echo "1";
}
else {
	//echo "Error: " . $mysql_qry . "<br>" . $conn->error;
	echo "-1";
}

$conn->close();


?>