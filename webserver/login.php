<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("users", $_POST['username'], $_POST['password'])) {
            echo "Berhasil Login";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "Semua Harus Terisi";
?>
