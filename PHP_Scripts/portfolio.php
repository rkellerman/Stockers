<?php

require "conn.php";

$playerID = intval($_POST["playerID"]);


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
                $change = $row[5];
                
        }
        
        if ($value > 0){
        
        
                echo $ticker . "!!!" . $price . "!!!" . $value . "!!!" . $change . "===";
        }
	
}

$conn->close();

?>

