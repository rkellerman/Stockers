<?php

require "conn.php";

$playerID = $_POST["person"];
// $playerID = 62 . "";

$mysql_qry = "select * from PlayerHistory where playerID = $playerID order by Date DESC;";   
$result = mysqli_query($conn, $mysql_qry);

$ret = "";

while ($row = $result->fetch_row()) {

        $id = $row[1];

        $mysql_qry = "select Ticker from StockInfo where ID = $id;";   
        $_result = mysqli_query($conn, $mysql_qry);
        
        $_row = $_result->fetch_row();
        $ticker = $_row[0];
        
	if ($row[2] == 0){
                $ret = $ret . "At " . $row[6] . " on " . $row[5] . ", you sold " . $row[3] . " share(s) of " . $ticker . " stock for a total price of $" . $row[4] . "!!!";
        
        }
        else {
                $ret = $ret . "At " . $row[6] . " on " . $row[5] . ", you purchased " . $row[3] . " share(s) of " . $ticker . " stock for a total price of $" . $row[4] . "!!!";
        
        
        }
}

if ($ret == ""){
        echo "-1";
        return;
}
echo $ret;


?>