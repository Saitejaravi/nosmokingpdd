<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Read raw JSON input
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);

    // Debugging: Log received data
    file_put_contents("debug_log.txt", "Received JSON: " . print_r($data, true) . "\n", FILE_APPEND);

    // Check if JSON data is valid
    if (!isset($data["username"], $data["password"])) {
        echo json_encode(["status" => "failure", "message" => "Username or password not provided"]);
        exit;
    }

    $username = $data["username"];
    $password = $data["password"];

    // Establish database connection
    require("conn.php");

    if ($con->connect_error) {
        echo json_encode(["status" => "failure", "message" => "Database connection failed: " . $con->connect_error]);
        exit;
    }

    // Prevent SQL Injection (Use Prepared Statement)
    $stmt = $con->prepare("SELECT lang FROM sign WHERE username = ? AND password = ?");
    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $stmt->store_result();

    if ($stmt->num_rows > 0) {
        // Fetch user language preference
        $stmt->bind_result($lang);
        $stmt->fetch();

        echo json_encode([
            "status" => "success",
            "message" => "Login successful",
            "lang" => $lang
        ]);
    } else {
        echo json_encode([
            "status" => "failure",
            "message" => "Invalid username or password"
        ]);
    }

    // Close connections
    $stmt->close();
    $con->close();
}
?>
