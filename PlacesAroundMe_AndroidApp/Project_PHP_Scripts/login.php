<?php
require "init.php";
$user_name=$_POST["user_name"];
$user_pass=$_POST["user_pass"];

$sql_query="select first_name,last_name,user_name,user_pass,group_id from user_info where user_name like '$user_name' or  user_pass like '$user_pass';";

$result=mysqli_query($con,$sql_query)
or die('Error in Query : $sql_query'.mysql_error());

while($row=mysqli_fetch_assoc($result))
{

$output[]=$row;
}
print (json_encode($output));
?>

