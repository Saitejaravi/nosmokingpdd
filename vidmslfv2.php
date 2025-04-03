<?php
// Database connection details
require("conn.php");
// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Directory where uploaded videos will be saved
$uploadDir = "videos/";

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_FILES['uploaded_file'])) {
    $file = $_FILES['uploaded_file'];
    $fileName = basename($file['name']);
    $uploadPath = $uploadDir . $fileName;

    if (move_uploaded_file($file['tmp_name'], $uploadPath)) {
        // File uploaded successfully, insert path into database
        $sql = "UPDATE videos SET vidmslfv2= ('$uploadPath')";

        if ($con->query($sql) === TRUE) {
            echo "Video path inserted into the database successfully";
        } else {
            echo "Error inserting video path into the database: " . $con->error;
        }
    } else {
        echo "Error uploading the file";
    }
} else {
    echo "Invalid request";
}

$con->close();
?>