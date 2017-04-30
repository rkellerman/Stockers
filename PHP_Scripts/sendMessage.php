<?php

require "conn.php";

$address = $_POST["email"];
$message = $_POST["message"];

// $address = "ryan";
// $message = "howdy mate";

$mysql_qry = "insert into Messages (user_email, message) values ('$address', '$message');";
$result = mysqli_query($conn, $mysql_qry);

echo "1";

$conn->close();
?>