<?php

require "conn.php";

$mysql_qry = "select ID, FirstName, LastName, PlayerValue from PlayerInfo;";   
$result = mysqli_query($conn, $mysql_qry);

$IDsToPlayers = array();
$playersToValues = array();

while ($row = $result->fetch_row()) {

	// echo "<p>CompanyID = " . $row[0] . " and SharesBought = " . $row[1] . "</p>";
	$name = $row[1] . " " . $row[2];
	$IDsToPlayers[$row[0]] = $name;
	$playersToValues[$name] = $row[3];
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
foreach ($playersToValues as $name => $value){

	echo $name . "!!!" . $value . "===";
}


?>