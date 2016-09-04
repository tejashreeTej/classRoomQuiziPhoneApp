<?php
$database_name="userinformation";
$server_name="localhost";
$sqluser_name="root";
$sql_pass="root";
//echo "Welcome to php";
$con=mysqli_connect($server_name,$sqluser_name,$sql_pass,$database_name);

if(!$con)
{
//echo "Connection Error . . .";
//echo "Connection Error . . .".mysqli_connect_error();
}
else
{
//echo "<h3>Connection Sucessful </h3>";
}
?>

~            