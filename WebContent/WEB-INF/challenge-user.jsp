<%@ page 	language 		= "java" 
			contentType 	= "text/html; charset=UTF-8"
   			pageEncoding 	= "UTF-8"
   			import 			= "com.openjpa.entities.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
		
		<title>Ikya | Création et visualisation des challenges</title>
	</head>
	<body>
		<p>Hello world !</p>
		
		<% 
		
			Account userTest = (Account)request.getAttribute("accountFinded");
			
			out.println("<p>"+userTest.getName()+"</p>");
			
		%>
		
	</body>
</html>