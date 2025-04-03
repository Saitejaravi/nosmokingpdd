<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

    // Get data from the request body as JSON
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    // Extract values from the JSON data
    $base64image = $data["base64image"];

    // Sanitize user inputs

    $imageData = base64_decode($base64image);

    // File handling
    $targetDirectory = "uploads/";
    $defaultImage = "demo.jpg";  // Default image filename

    // Check if the file is uploaded successfully
    if ($imageData !== false) {
        $profileimage = $targetDirectory . time() . ".jpg";  // Unique filename based on timestamp
        file_put_contents($profileimage, $imageData);
    } else {
        // Use default image if decoding fails or no file is uploaded
        $profileimage = $targetDirectory . $defaultImage;
    } 

    // Prepare SQL statement with prepared statement
    $sql = "UPDATE image SET dp = ?";

    // Create a prepared statement
    $stmt = $con->prepare($sql);

    // Bind parameters
    $stmt->bind_param("s", $profileimage);

    // Execute the statement
    if ($stmt->execute()) {
        echo "Data inserted successfully";
    } else {
        echo "Error: " . $con->error;
    }

    // Close the statement and connection
    $stmt->close();
    $con->close();
}
?>
