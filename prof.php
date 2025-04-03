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
        $sql = "SELECT name, contact, age,email,dp,lang, gender FROM sign WHERE username = '$username'";
        $result = $con->query($sql);

        if ($result->num_rows > 0) {
            // Fetch the data
            $row = $result->fetch_assoc();
            $profileImage = $row['dp'];
            if (!empty($profileImage) && file_exists($profileImage)) {
                // Convert the image to base64
                $base64Image = base64_encode(file_get_contents($profileImage));
                $response['profileimage'] = $base64Image;
            } else {
                // Set a default image or handle the absence of the image
                $base64Image = '';
                $response['profileimage'] = ''; // You can set a default image here if needed
            }

            // Extract the values you need and concatenate them into a string
            $string_data = $row['name'] . ', ' . $row['contact'] . ', ' . $row['age'] . ', ' .$row['email'] . ', ' . $base64Image . ', ' . $row['lang'] . ', ' . $row['gender'];

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
