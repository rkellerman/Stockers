<?php

require "conn.php";

/*
$playerID = intval('2');;

$ticker = 'msft';
$shares = intval('10');
$money = floatval('100000000.01');
*/




$playerID = intval($_POST["playerID"]);
$ticker = $_POST["ticker"];
$shares = intval($_POST["shares"]);
$money = floatval($_POST["money"]);


//echo $playerID . $ticker . $shares . $money;

$mysql_qry = "select * from CurrentStockPricing where Ticker like '$ticker';";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);
$price = $row["Price"];

//echo $price;

$mysql_qry = "select * from StockInfo where Ticker like '$ticker';";
$result = mysqli_query($conn, $mysql_qry);

$row = mysqli_fetch_assoc($result);
$companyID = $row["ID"];

//echo '<p>Company ID is ' . $companyID . '</p>';

$amount = $shares * $price;
$money = $money - $amount;

if ($money < 0.0){
	echo '-2.0';
	$conn->close();
	return;
}

$date = date('m/d/Y');
$time = date('H:i');

//echo $date;
//echo $time;

$mysql_qry = "insert into PlayerHistory (PlayerID, CompanyID, BuySell, Shares, Price, Date, Time) values ($playerID, $companyID, 1, $shares, $amount, '$date', '$time');";
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



