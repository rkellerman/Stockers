<?php

require "conn.php";

$email = $_POST["email"];
//$email = "ryan";

$mysql_qry = "select * from Messages order by message_id;";
$result = mysqli_query($conn, $mysql_qry);

$string = "";

while ($row = $result->fetch_row()) {

	if ($row[1] == $email){
                
                $string = $string . "M" . $row[1] . "===" . $row[2] . "===" . $row[3] . "!!!";
        }
        else {
                $string = $string . "N" . $row[1] . "===" . $row[2] . "===" . $row[3] . "!!!";
        }
}

echo $string;
$conn->close();
?>