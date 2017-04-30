<?php

$ticker = $_POST["ticker"];

// $ticker = "GOOG";

$s = file_get_contents("https://chart.finance.yahoo.com/z?s=$ticker&t=6m&q=l&l=on&z=s&p=m50,m200");
$base64 = base64_encode($s);

echo $base64;

?>