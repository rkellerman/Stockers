<?php
global $db_name;
global $mysql_username;
global $mysql_username;
global $server_name;
global $conn;

$db_name = "2312545_stockers";
$mysql_username = "2312545_stockers";
$mysql_password = "Stockers1234";
$server_name = "pdb9.awardspace.net";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);


?>