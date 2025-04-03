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

        // Query to check login credentials
        $sql = "SELECT name, contact, age,email, gender,ind,bwk1,bwk2,bwk3,bwk4,bwk5,bwk6,bwk7,bwk8,bwk9,bwk10,bwk11,bwk12 FROM sign WHERE username = '$username'";
        $result = $con->query($sql);

        if ($result->num_rows > 0) {
            // Fetch the data
            $row = $result->fetch_assoc();

            // Extract the values you need and concatenate them into a string
            $string_data = $row['name'] . ', ' . $row['contact'] . ', ' . $row['age'] . ', ' .$row['email'] . ', ' . $row['gender'] . ', ' . $row['ind'] . ', ' . $row['bwk1'] . ', ' . $row['bwk2'] . ', ' . $row['bwk3'] . ', ' . $row['bwk4'] . ', ' . $row['bwk5'] . ', ' . $row['bwk6'] . ', ' . $row['bwk7'] . ', ' . $row['bwk8'] . ', ' . $row['bwk9'] . ', ' . $row['bwk10'] . ', ' . $row['bwk11'] . ', ' . $row['bwk12'];

            // Login successful
        
            $response = $string_data;
        } else {
            // Login failed
            $response['status'] = 'failure';
            $response['message'] = 'Invalid username or password';
        }

        // Close the database connection
        $con->close();
    } else {
        $response['status'] = 'failure';
        $response['message'] = 'Username or password not provided';
    }

    echo json_encode($response);
}
