<?php
require "conn.php";
include_once('class.yahoostock.php');


  $objYahooStock = new YahooStock;

$objYahooStock->addFormat("snl1d1t1cvwoj1rded"); 

$objYahooStock->addStock('msft');
$objYahooStock->addStock('yhoo');
$objYahooStock->addStock('goog'); 
$objYahooStock->addStock('vgz');
$objYahooStock->addStock('vz');
$objYahooStock->addStock('t');
$objYahooStock->addStock('tgt');
$objYahooStock->addStock('aapl');
$objYahooStock->addStock('hpq');
$objYahooStock->addStock('cmcsa');
$objYahooStock->addStock('dish');
$objYahooStock->addStock('twx');
$objYahooStock->addStock('cbs');

foreach( $objYahooStock->getQuotes() as $code => $stock)
{
  /*
    ?>
    Code: <?php echo $stock[0]; ?> <br />
    Name: <?php echo $stock[1]; ?> <br />
    Last Trade Price: <?php echo $stock[2]; ?> <br />
    Last Trade Date: <?php echo $stock[3]; ?> <br />
    Last Trade Time: <?php echo $stock[4]; ?> <br />
    Change and Percent Change: <?php echo $stock[5]; ?> <br />
    Volume: <?php echo $stock[6]; ?> <br /><br />
    <?php
    */


   //$sql = "insert into CurrentStockPricing (CompanyName, Ticker, Price, Date, Time, ChangePercent, Volume, Year, Open, Cap, PE, DPS, EPS, High) values ($stock[1], $stock[0], $stock[2], $stock[3], $stock[4], $stock[5], $stock[6], $stock[7], '$stock[8]', '$stock[9]', '$stock[10]', '$stock[11]', '$stock[12]', '$stock[13]');";
    //$sql = "insert into StockInfo (CompanyName, Ticker) values ($stock[1], $stock[0]);";
   $sql = "UPDATE CurrentStockPricing SET Price=$stock[2],Date=$stock[3],Time=$stock[4],ChangePercent=$stock[5],Volume=$stock[6], Year=$stock[7], Open=$stock[8], Cap='$stock[9]', PE=$stock[10], DPS=$stock[11], EPS=$stock[12], High=$stock[13] where Ticker like $stock[0];";
   echo "<p>" . $sql . "</p>";
   //mysql_select_db('test_db');

   if ($conn->query($sql) === TRUE){
  echo "Insert/Update Successful";
}
else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
   
}

$conn->close();