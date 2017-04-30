<?php

require "conn.php";

$recipient_id = $_POST["person"];
// $recipient_id = 63 . "";

$IDsToPlayers = array();
$playersToValues = array();

$mysql_qry = "SELECT friends FROM PlayerInfo WHERE ID = '" . $recipient_id . "'";
$_result = mysqli_query($conn, $mysql_qry);

while ($_row = $_result->fetch_row()) {
                        // echo "fack";
	$friends = unserialize($_row[0]);
	$friends[] = $sender_id;    
}  


foreach($friends as $id){

	$mysql_qry = "select ID, FirstName, LastName, PlayerValue from PlayerInfo where ID = '$id';";   
	$result = mysqli_query($conn, $mysql_qry);

	while ($row = $result->fetch_row()) {

	// echo "<p>CompanyID = " . $row[0] . " and SharesBought = " . $row[1] . "</p>";
		$name = $row[1] . " " . $row[2];
		$IDsToPlayers[$row[0]] = $name;
		$playersToValues[$name] = $row[3];
	}
}

foreach ($IDsToPlayers as $playerID => $name){

	$mysql_qry = "select CompanyID, Shares from PlayerHistory where playerID = $playerID and BuySell = 1;";   
	$result = mysqli_query($conn, $mysql_qry);

	$array = array();

	while ($row = $result->fetch_row()) {

		// echo "<p>CompanyID = " . $row[0] . " and SharesBought = " . $row[1] . "</p>";

		$array[$row[0]] += $row[1];
	}

	$mysql_qry = "select CompanyID, Shares from PlayerHistory where playerID = $playerID and BuySell = 0;";   
	$result = mysqli_query($conn, $mysql_qry);

	while ($row = $result->fetch_row()) {

		//echo "<p>CompanyID = " . $row[0] . " and SharesSold = " . $row[1] . "</p>";

		$array[$row[0]] = $array[$row[0]] - $row[1];
	}



	foreach($array as $key => $value){

		$mysql_qry = "select Ticker from StockInfo where ID = $key";   
		$result = mysqli_query($conn, $mysql_qry);
		
		while ($row = $result->fetch_row()) {
			
			$ticker = $row[0];
		}
		
		$mysql_qry = "select * from CurrentStockPricing where Ticker like '$ticker';";
		$result = mysqli_query($conn, $mysql_qry);
		
		while ($row = $result->fetch_row()) {
			
			$price = $row[2];
			
		}

		$total = $value * $price;
		$playersToValues[$name] += $total;
		
	}

}

arsort($playersToValues);
$ret = "";
foreach ($playersToValues as $name => $value){

	$ret = $ret . $name . "!!!" . $value . "===";
}

if ($ret == ""){
        echo "-1";
        return;
}
echo $ret;


?>