<?php
require "init.php";

$sql_query="select first_name,last_name,longitude,latitude,message,date,category,group_id from user_post";

$result=mysqli_query($con,$sql_query)
or die('Error in Query : $sql_query'.mysql_error());


while($row=mysqli_fetch_assoc($result))
{

$output[]=$row;
}
print (json_encode($output));
?>
