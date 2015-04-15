<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Page</title>
</head>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<body>
<h1>This JSP Page is used to insert the values, delete a particular value and update a value</h1>

	<script>
		$(function(){
			
			
			alert("Jquery alert.");
			
			var newsite= {
					id:6,
					latitude:31.32,
					longitude:59.54,
					name:"New Site Name"};
			//updatesite(6, newsite);
			//addsite(newsite);
			//removesite(8);
		});
		
		function addsite(site){
			$.ajax
			({
				url:"api/site",
				type: "post",
				contentType:"application/json; charset=utf-8",
				dataType: "json",
				data: JSON.stringify(site),
				success: function(response){
					
					console.log("Post success");
				}
			});
		}
		
		function removesite(id){		
			$.ajax(
					{
						url:"api/site/"+id,
						type: "delete",
						//data: JSON.stringify("_method"),
						contentType:"application/json; charset=utf-8",
						dataType: "json",
						success: function(data) {
							console.log(data);
						}
					});
			}
		
		function updatesite(id, site)
		{
			$.ajax(
			{
				url:"api/site/"+id,
				type: "put",
				contentType:"application/json",
				dataType: "json",
				data: JSON.stringify(site),
				success: function(response) {
					console.log("Data Modified");
				},
				error: function(response) {
					console.log("Failure");
				}
			});
		}
		
	</script>



</body>
</html>