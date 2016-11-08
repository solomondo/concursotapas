<?php

include_once('config.php');

if($_SERVER['REQUEST_METHOD'] == "GET"){
	// Get data
	$name = isset($_GET['name']) ? mysql_real_escape_string($_GET['name']) : "";	
	$email = isset($_GET['email']) ? mysql_real_escape_string($_GET['email']) : "";
	$password = isset($_GET['pwd']) ? mysql_real_escape_string($_GET['pwd']) : "";
	$status = isset($_GET['status']) ? mysql_real_escape_string($_GET['status']) : ""; 
	
	// Insert data into data base
	$sql = "INSERT INTO apirest_test.users (ID, name, email, password, status) VALUES (NULL, '$name', '$email', '$password', '$status');";
	$qur = mysql_query($sql);
	if($qur){
		$json = array("status" => 1, "msg" => $name . " " . $email . " Done User added!");
	}else{
		$json = array("status" => 0, "msg" => "Error adding user!");
	}
}else{
	$json = array("status" => 0, "msg" => "Request method not accepted");
}

@mysql_close($conn);

/* Output header */
header('Content-type: application/json');
echo json_encode($json);
?>