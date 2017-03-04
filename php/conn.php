<?php
global $db_name;
global $mysql_username;
global $mysql_username;
global $server_name;
global $conn;

$db_name = "Stockers";
$mysql_username = "root";
$mysql_password = "root";
$server_name = "localhost";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);


?>