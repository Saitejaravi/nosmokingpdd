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
        $sql = "SELECT points, mon_sav,mon,rs,ind,log_dt,dq_dt, days FROM sign WHERE username = '$username'";
        $result = $con->query($sql);

        if ($result->num_rows > 0) {
            // Fetch the data
            $row = $result->fetch_assoc();

            // Extract the values you need and concatenate them into a string
            $string_data = $row['points'] . ', ' . $row['mon_sav'] .  ', '. $row['mon'] .  ', ' . $row['rs'] . ', ' . $row['ind'] . ', ' . $row['log_dt'] . ', ' . $row['dq_dt'] . ', ' . $row['days'];

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
