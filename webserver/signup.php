<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password']) && isset($_POST['role'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("users", $_POST['fullname'], $_POST['email'], $_POST['username'], $_POST['password'], $_POST['role'])) {
            echo "Registrasi Berhasil";
        } else echo "Registrasi Gagal";
    } else echo "Error: Koneksi Database";
} else echo "Semua Harus Terisi";
?>
