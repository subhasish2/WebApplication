<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.Clustering.PDBSCAN"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.Scanner"%>
<%@ page import="java.io.File"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Geo Clustering</title>
<style>
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div id="map"></div>
	<%
		String filename = "C:/Users/Subhasish/git/WebApp/Login/csv/bike.csv";
		PDBSCAN d = new PDBSCAN(filename, 1000, 2);
		String outfile = d.PDBSCAN_Clustering();
		ArrayList<String> centers = new ArrayList<String>();
	%>
	<script>
		function initMap() {

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 10,
				center : {
					lat : 22.5613695,
					lng : 88.4144755
				}
			});

			// Create an array of alphabetical characters used to label the markers.
			var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

			var markers = locations.map(function(location, i) {
				return new google.maps.Marker({
					position : location,
					label : labels[i % labels.length],
					map : map
				});
			});

			// Add a marker clusterer to manage the markers.
			// var markerCluster = new MarkerClusterer(map, markers,
			//  {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
			var circles = centers.map(function(center, i) {
				return new google.maps.Circle({
					center : center,
					radius : 1000,
					strokeColor : "#0000FF",
					strokeOpacity : 0.3,
					strokeWeight : 1,
					fillColor : "#0000FF",
					fillOpacity : 0.4,
					map : map
				});
			});

		}

		var locations = [
	<%File file = new File(outfile);
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String header = scan.nextLine();
				String[] headpart = header.split(" ");
				String[] cl_info = headpart[1].split(":");
				int cl_size = Integer.parseInt(cl_info[1]);
				String center = scan.nextLine();
				centers.add(center.substring(0, center.length() - 1));
				out.println(center);
				if (scan.hasNext())
					for (int i = 0; i < cl_size - 1; i++)
						out.println(scan.nextLine());
			}%>
		]

		var centers = [
	<%for (int i = 0; i < centers.size() - 1; i++)
				out.println(centers.get(i) + ",");
			out.println(centers.get(centers.size() - 1));%>
		]

		//
	</script>
	//
	<script
		src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
		//
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCTPHzMXvTJ7b52YUWqPTAklkWdJny54S8&callback=initMap">
		
	</script>
</body>
</html>