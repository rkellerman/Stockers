<?php 

require "conn.php";

$method = $_POST["method"];
$recipient_email = $_POST["recipient"];  // ID of the recipient
$sender_id = $_POST["sender"];

/*
$method = "add";
$recipient_email = "frank@rutgers.edu";
$sender_id = "" . 62;
*/


if ($method == "add"){
	$mysql_qry = "select ID from PlayerInfo where Email like '$recipient_email';";
	$result = mysqli_query($conn, $mysql_qry);
	$recipient_id = -1;
	while ($row = $result->fetch_row()) {

	// echo $row[0] . " " . $row[1] . " " . $row[2] . " " . $row[3] . " " . $row[4] . " " . $row[5] . " " . $row[6];
		$recipient_id = $row[0];
	}
	if ($recipient_id == -1){
		echo "-1";
		return;
	}
        // echo $recipient_id;
	$mysql_qry = "SELECT ID FROM PlayerInfo WHERE ID = '" . $recipient_id . "'";
	$result = mysqli_query($conn, $mysql_qry);
	while ($row = $result->fetch_row()){
                // echo "stupid";
		$mysql_qry = "SELECT * FROM friend_requests WHERE sender = '" . $sender_id . "' AND recipient = '" . $recipient_id . "'";
		$_result = mysqli_query($conn, $mysql_qry);
		if (mysqli_num_rows($_result) == 0){
                        // echo "penis";
			$mysql_qry = "INSERT INTO friend_requests SET sender = '" . $sender_id . "', recipient = '" . $recipient_id . "'";
			$__result = mysqli_query($conn, $mysql_qry);
		}
                else {
                        echo "-2";
                }
	}
}
else if ($method == "show_requests"){
	$ret = "";
	$mysql_qry = "SELECT sender FROM friend_requests WHERE recipient = '" . $sender_id . "'";
	$result = mysqli_query($conn, $mysql_qry);

	while ($row = $result->fetch_row()){
		$mysql_qry = "SELECT Email FROM PlayerInfo WHERE ID = '" . intval($row[0]) . "';";
		$_result = mysqli_query($conn, $mysql_qry);
                // echo $_result;
		while ($_row = $_result->fetch_row()){
			$ret = $ret . $_row[0] . "!!!";
		}
	}
	if ($ret == ""){
		echo "-1";
		return;
	}
	echo $ret;
}
else if ($method == "show_friends"){
	$ret = "";
	$mysql_qry = "SELECT friends FROM PlayerInfo WHERE ID = '" . $sender_id . "'";
	$result = mysqli_query($conn, $mysql_qry);
	while ($row = $result->fetch_row()){
		$friends = unserialize($row[0]);

		if(isset($friends[0])) {

			foreach($friends as $friend) {
				$mysql_qry = "SELECT Email FROM PlayerInfo WHERE ID = '" . $friend . "'";
				$_result = mysqli_query($conn, $mysql_qry);
				while ($_row = $_result->fetch_row()){
					$ret = $ret . $_row[0] . "!!!";
				}
			}

		}
	}
	if ($ret == ""){
		echo "-1";
		return;
	}
	echo $ret;

}
else if ($method == "accept"){
        $mysql_qry = "select ID from PlayerInfo where Email like '$recipient_email';";
	$result = mysqli_query($conn, $mysql_qry);
        $recipient_id = -1;
	while ($row = $result->fetch_row()) {

	// echo $row[0] . " " . $row[1] . " " . $row[2] . " " . $row[3] . " " . $row[4] . " " . $row[5] . " " . $row[6];
		$recipient_id = $row[0];
	}
	if ($recipient_id == -1){
		echo "-1";
		return;
	}
        
        // echo $recipient_id;
        $temp = $recipient_id;
        $recipient_id = $sender_id;
        $sender_id = $temp;
        $mysql_qry = "SELECT * FROM friend_requests WHERE sender = '" . $sender_id . "' AND recipient = '" . $recipient_id . "'";
	$result = mysqli_query($conn, $mysql_qry);
        if (mysqli_num_rows($result) > 0){
        
                // echo "peep";
        
                $mysql_qry = "SELECT friends FROM PlayerInfo WHERE ID = '" . $sender_id . "'";
                $_result = mysqli_query($conn, $mysql_qry);
                while ($_row = $_result->fetch_row()) {
                        // echo "hi";
                        $friends = unserialize($_row[0]);
                        $friends[] = $recipient_id;   
                }
                
                $mysql_qry = "UPDATE PlayerInfo SET friends = '" . serialize($friends) . "' WHERE ID = '" . $sender_id . "'";
                $_result = mysqli_query($conn, $mysql_qry);
                
                $mysql_qry = "SELECT friends FROM PlayerInfo WHERE ID = '" . $recipient_id . "'";
                $_result = mysqli_query($conn, $mysql_qry);
                
                while ($_row = $_result->fetch_row()) {
                        // echo "fack";
                        $friends = unserialize($_row[0]);
                        $friends[] = $sender_id;    
                }  
                
                $mysql_qry = "UPDATE PlayerInfo SET friends = '" . serialize($friends) . "' WHERE ID = '" . $recipient_id . "'";
                $_result = mysqli_query($conn, $mysql_qry);
        }
        
        $mysql_qry = "DELETE FROM friend_requests WHERE sender = '" . $sender_id . "' AND recipient = '" . $recipient_id . "'";
	$result = mysqli_query($conn, $mysql_qry);
        
        
}

?>