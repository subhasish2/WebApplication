<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distance Calculator</title>
</head>
<body>

<h2 align="center">Distance Calculator</h2>
<form method="get" action="DistanceServlet">
Location 1:<br><br>
Latitude: <input type="text" name="lat1" size=20 value="<%=request.getParameter("lat1")%>">
Longitude: <input type="text" name="lng1" size=20 value="<%=request.getParameter("lng1")%>">
<br><br>Location 2:<br><br>
Latitude: <input type="text" name="lat2" size=20 value="<%=request.getParameter("lat2")%>">
Longitude: <input type="text" name="lng2" size=20 value="<%=request.getParameter("lng2")%>">
<br><br><input type="submit" value="Submit">
</form>
<br>Distance: <input type="text" name="distance" value="<%=request.getAttribute("distance")%>"/> in meters

</body>
</html>