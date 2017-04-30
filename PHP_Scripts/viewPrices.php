<?php
require "conn.php";


$mysql_qry = "select * from CurrentStockPricing;";
$result = mysqli_query($conn, $mysql_qry);

while ($row = $result->fetch_row()) {

	echo $row[0] . ", " . $row[1] . ", " . $row[2] . ", " . $row[3] . ", " . $row[4] . ", " . $row[5] . ", " . $row[6] . " ^ ";

}

$conn->close();