<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "phpdatabase";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $operation = $_POST['operation'] ?? null;
    $tourist_spot = $_POST['tourist_spot'] ?? null;
    $description = $_POST['description'] ?? null;
    $location = $_POST['location'] ?? null;
    $rating = $_POST['rating'] ?? null;
    $image_path = $_POST['image_path'] ?? null;
    $modified_by = $_POST['modified_by'] ?? null;
    $aadhar = $_POST['aadhar'] ?? null;

    switch ($operation) {
        case "create":
            $sql = "INSERT INTO tourist_spots (tourist_spot, description, location, rating, image_path, modified_by, aadhar) 
                    VALUES ('$tourist_spot', '$description', '$location', $rating, '$image_path', '$modified_by', '$aadhar')";
            break;

        case "update":
            $sql = "UPDATE tourist_spots 
                    SET description='$description', location='$location', rating=$rating, image_path='$image_path', modified_by='$modified_by',aadhar='$aadhar' 
                    WHERE tourist_spot='$tourist_spot'";
            break;

        case "delete":
            $sql = "DELETE FROM tourist_spots 
                    WHERE tourist_spot='$tourist_spot'";
            break;

        default:
            echo "Invalid operation.";
            $conn->close();
            exit();
    }

    if ($conn->query($sql) === TRUE) {
        echo "<h2>".ucfirst($operation) . " operation successful.</h2>";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

else{
    $sql = "SELECT * FROM tourist_spots";
    $result = $conn->query($sql);
    echo "<h2 style='text-align:center;'>Details in Tourist Spots</h2>";
    echo "<style>tr,td,th{ border:1px solid black;'</style>";
    if ($result->num_rows > 0) {
        echo "<table style='width: 100%; border-collapse: collapse;'>";
        echo "<tr><th>Tourist Spot</th><th>Description</th><th>Location</th><th>Rating</th><th>Image Path</th><th>Modified By</th><th>Aadhar</th></tr>";
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>" . $row['tourist_spot'] . "</td>";
            echo "<td>" . $row['description'] . "</td>";
            echo "<td>" . $row['location'] . "</td>";
            echo "<td>" . $row['rating'] . "</td>";
            echo "<td><img src='" . $row['image_path'] . "' alt='Image of " . $row['tourist_spot'] . "' width='100'></td>";
            echo "<td>" . $row['modified_by'] . "</td>";
            echo "<td>" . $row['aadhar'] . "</td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "No records found.";
    }
}

$conn->close();
?>
