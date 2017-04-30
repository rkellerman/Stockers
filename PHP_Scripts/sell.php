<?php

require "conn.php";
include_once('class.yahoostock.php');

/*
$playerID = intval('6');;

$ticker = 'aapl';
$shares = intval('13');
$money = floatval('100000000.01');
*/


$playerID = intval($_POST["playerID"]);
$ticker = $_POST["ticker"];
$shares = intval($_POST["shares"]);
$money = floatval($_POST["money"]);



$mysql_qry = "select * from CurrentStockPricing where Ticker like '$ticker';";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);
$price = $row["Price"];

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

$amount = $shares * $price;
$money = $money + $amount;

$date = date('m/d/Y');
$time = date('H:i');

//echo $date;
//echo $time;

$mysql_qry = "insert into PlayerHistory (PlayerID, CompanyID, BuySell, Shares, Price, Date, Time) values ($playerID, $companyID, 0, $shares, $amount, '$date', '$time');";
//echo '<p>' . $mysql_qry . '</p>';

if ($conn->query($mysql_qry) === TRUE){
	//echo 'butts';
}
else {

}

$mysql_qry = "update PlayerInfo set PlayerValue=$money where ID = $playerID;";
//echo '<p>' . $mysql_qry . '</p>';

if ($conn->query($mysql_qry) === TRUE){
	echo $money;
}
else {
	echo '-1.0';

}

$conn->close();

