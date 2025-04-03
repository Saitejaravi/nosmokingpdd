<?php
header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment; filename="data.xlsx"');

$servername = "localhost";
$username = "your_username";
$password = "your_password";
$dbname = "your_database";

require("conn.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Query to fetch data
$sql = "SELECT * FROM sign";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    // Output column headers
    $columns = array_keys($result->fetch_assoc());
    echo implode("\t", $columns) . "\n";
    
    // Reset pointer and fetch data
    $result->data_seek(0);
    while ($row = $result->fetch_assoc()) {
        echo implode("\t", array_values($row)) . "\n";
    }
} else {
    echo "No data found";
}

$con->close();
?>
