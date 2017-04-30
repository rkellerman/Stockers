<?php

require "conn.php";
$email = $_POST["email"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from PlayerInfo where email like '$email' and password like '$user_pass';";
$result = mysqli_query($conn, $mysql_qry);
$string = "";
if (mysqli_num_rows($result) > 0){

	$row = mysqli_fetch_assoc($result);
	$name = $row["FirstName"];
	$ID = $row["ID"];
	$pvalue = $row["PlayerValue"];
	$string = $string . $name . " " . $ID . " " . $pvalue;


	$mysql_qry = "select CompanyID, Shares from PlayerHistory where playerID = $ID and BuySell = 1;";   
	$result = mysqli_query($conn, $mysql_qry);

	$array = array();

	while ($row = $result->fetch_row()) {

		// echo "<p>CompanyID = " . $row[0] . " and SharesBought = " . $row[1] . "</p>";

		$array[$row[0]] += $row[1];
	}

	$mysql_qry = "select CompanyID, Shares from PlayerHistory where playerID = $ID and BuySell = 0;";   
	$result = mysqli_query($conn, $mysql_qry);

	while ($row = $result->fetch_row()) {

		//echo "<p>CompanyID = " . $row[0] . " and SharesSold = " . $row[1] . "</p>";

		$array[$row[0]] = $array[$row[0]] - $row[1];
	}

	$netWorth = 0;

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
        $netWorth += $total;
	
	}
        
        $netWorth += $pvalue;

	echo $string . " " . $netWorth;
}
else {
	echo "-1";
}

$conn->close();
?>