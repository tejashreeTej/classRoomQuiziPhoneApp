<?php
require "init.php";
$first_name=$_POST["first_name"];
$last_name=$_POST["last_name"];
$user_name=$_POST["user_name"];
$user_pass=$_POST["user_pass"];
$group_id=$_POST["group_id"];

$sql_query="insert into user_info(first_name,last_name,user_name,user_pass,group_id) values('$first_name','$last_name','$user_name','$user_pass','$group_id');";
if(mysqli_query($con,$sql_query))
{
//echo "Syccessfully inserteted";
}
else{
//echo "not inserteted";
}
?>
~       