<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Decode the JSON data
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    if (isset($_POST["username"])) {
        $username = $_POST["username"];

        // Establish the database connection
        require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

        $totalRowsQuery = "SELECT COUNT(*) as totalRows FROM sign";
        $maleRowsQuery = "SELECT COUNT(*) as maleRows FROM sign WHERE gender = 'male'";
        $femaleRowsQuery = "SELECT COUNT(*) as femaleRows FROM sign WHERE gender = 'female'";

        $totalRowsResult = $con->query($totalRowsQuery);
        $maleRowsResult = $con->query($maleRowsQuery);
        $femaleRowsResult = $con->query($femaleRowsQuery);

        $totalRows = $totalRowsResult->fetch_assoc()['totalRows'];
        $maleRows = $maleRowsResult->fetch_assoc()['maleRows'];
        $femaleRows = $femaleRowsResult->fetch_assoc()['femaleRows'];


        

        // Extract the values you need and concatenate them into a string
        $string_data = $totalRows . ', ' . $maleRows . ', ' . $femaleRows ;

        // Login successful
        
        $response = $string_data;
        

        // Close the database connection
        $con->close();
    } else {
        $response['status'] = 'failure';
        $response['message'] = 'Username or password not provided';
    }

    echo json_encode($response);
}
