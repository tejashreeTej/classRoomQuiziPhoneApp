<?php
require "init.php";

$first_name=$_POST["first_name"];
$last_name=$_POST["last_name"];
$longitude=$_POST["longitude"];
$latitude=$_POST["latitude"];
$message=$_POST["message"];
$date=$_POST["date"];
$category=$_POST["category"];
$group_id=$_POST["group_id"];

$sql_query="insert into user_post(first_name,last_name,longitude,latitude,message,date,category,group_id) values('$first_name','$last_name','$longitude','$latitude','$message','$date','$category','$group_id');";
if(mysqli_query($con,$sql_query))
{
print "upload Successfull.";
}
else{
print "upload fail.";
}
?>
