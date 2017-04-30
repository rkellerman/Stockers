<?php
require "conn.php";

$ticker = $_POST["ticker"];
$playerID = intval($_POST["playerID"]);

$mysql_qry = "select * from CurrentStockPricing where Ticker like '$ticker';";
$result = mysqli_query($conn, $mysql_qry);

$string = "";

while ($row = $result->fetch_row()) {

	$string = $string . $row[0] . "===" . $row[1] . "===" . $row[2] . "===" . $row[3] . "===" . $row[4] . "===" . $row[5] . "===" . $row[6] . "===" . $row[7] . "===" . $row[8] . "===" . $row[9] . "===" . $row[10] . "===" . $row[11] . "===" . $row[12] . "===" . $row[13];
}

$mysql_qry = "select * from StockInfo where Ticker like '$ticker';";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);
$companyID = $row["ID"];

$mysql_qry = "select * from PlayerHistory where CompanyID = $companyID and BuySell = 1 and PlayerID = $playerID;";
$result = mysqli_query($conn, $mysql_qry);

$rows = mysqli_num_rows($result);
//echo "<p>There are " . $rows . " rows</p>";

$sharesOwned = 0;

while ($row = $result->fetch_row()) {

	// echo "<p>Bought " . intval($row[3]) . "</p>";
	$sharesOwned = $sharesOwned + intval($row[3]);
	// echo "<p>Value here is " . $row[3] . "</p>";
}



$mysql_qry = "select * from PlayerHistory where CompanyID = $companyID and BuySell = 0 and PlayerID = $playerID;";
$result = mysqli_query($conn, $mysql_qry);

$rows = mysqli_num_rows($result);
//echo "<p>There are " . $rows . " rows</p>";


while ($row = $result->fetch_row()) {
	// echo "<p>Sold " . intval($row[3]) . "</p>";
	$sharesOwned = $sharesOwned - intval($row[3]);
	// echo "<p>Value here is " . intval($row[3]) . "</p>";
}

// echo "<p>SharesOwned = " . $sharesOwned . "</p>";


if ($sharesOwned < $shares){
	echo '-2.0';
	$conn->close();
	return;
}

echo $string . "===" . $sharesOwned;


$conn->close();