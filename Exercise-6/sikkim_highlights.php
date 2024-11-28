<?php
// Load XML file
$xmlFile = "sikkim_highlights.xml";
$xml = simplexml_load_file($xmlFile);

// Check if XML is loaded successfully
if (!$xml) {
    die("Error: Cannot load XML file.");
}

// Function to filter attractions
function filterAttractions($xml, $category = null, $region = null) {
    $results = [];
    foreach ($xml->attraction as $attraction) {
        $matchCategory = ($category === null || $category === "" || (string)$attraction->category === $category);
        $matchRegion = ($region === null || $region === "" || (string)$attraction->region === $region);

        if ($matchCategory && $matchRegion) {
            $results[] = $attraction;
        }
    }
    return $results;
}

// Handle form submission and get selected filter values
$category = isset($_POST['category']) ? $_POST['category'] : "";
$region = isset($_POST['region']) ? $_POST['region'] : "";

// Filter the attractions based on the selected category and region, if any
if ($category || $region) {
    $filteredAttractions = filterAttractions($xml, $category, $region);
} else {
    // Show all attractions if no filter is applied
    $filteredAttractions = $xml->attraction;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sikkim Highlights</title>
    <style>
         body {
            font-family: 'Arial', sans-serif;
            background-color: #f1f1f1;
            margin: 0;
            padding: 0;
            color: #333;
            text-align:center;
        }
        h1 {
            color: #2c3e50;
            font-size: 36px;
            margin-bottom: 20px;
        }
        h2 {
            color: #16a085;
            font-size: 28px;
            margin-bottom: 20px;
        }
        form {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 0 auto 20px;
            max-width: 500px;
        }
        label {
            font-size: 16px;
            color: #555;
            margin-right: 10px;
        }
        select {
            padding: 10px;
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: #f9f9f9;
        }
        button {
            background-color: #2ecc71;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #27ae60;
        }
        table {
            margin-top: 40px;
            width: 80%;
            margin-left: auto;
            margin-right: auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px 20px;
            text-align: left;
            font-size: 16px;
        }
        th {
            background-color: #16a085;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .no-results {
            color: #e74c3c;
            font-size: 18px;
            font-weight: bold;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .card {
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <h1>Sikkim Highlights</h1>

    <!-- Filter Form -->
    <h2>Filter Attractions</h2>
    <form action="" method="POST">
        <label for="category">Category:</label>
        <select id="category" name="category">
            <option value="">All</option>
            <option value="Nature">Nature</option>
            <option value="Adventure">Adventure</option>
            <option value="Spiritual">Spiritual</option>
            <option value="Heritage">Heritage</option>
        </select>
        <br><br>

        <label for="region">Region:</label>
        <select id="region" name="region">
            <option value="">All</option>
            <option value="East Sikkim">East</option>
            <option value="West Sikkim">West</option>
            <option value="North Sikkim">North</option>
            <option value="South Sikkim">South</option>
        </select>
        <br><br>

        <button type="submit">Filter</button>
    </form>
    <!-- Display Filtered Attractions -->
    <h2>Attractions List</h2>
    <?php
    if (count($filteredAttractions) > 0) {
        echo "<table>";
        echo "<tr><th>Name</th><th>Category</th><th>Region</th><th>Description</th></tr>";
        foreach ($filteredAttractions as $attraction) {
            $name = $attraction->name;
            $category = $attraction->category;
            $region = $attraction->region;
            $description = $attraction->description;
            echo "<tr>";
            echo "<td>$name</td>";
            echo "<td>$category</td>";
            echo "<td>$region</td>";
            echo "<td>$description</td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "<p class='no-results'>No attractions found for the selected filters.</p>";
    }
    ?>
</body>
</html>
