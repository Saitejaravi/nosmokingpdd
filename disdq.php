<?php
// Database connection details
require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Get video ID from the POST data
$username = "1";

// Fetch video path from the database based on the username
$sql = "SELECT viddq FROM videos WHERE id = '$username'";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $videoPath = $row['viddq'];

    // Return the video path as JSON
    echo json_encode(['video_path' => $videoPath]);
} else {
    echo json_encode(['error' => 'Video not found for the given username']);
}

$con->close();
?>
