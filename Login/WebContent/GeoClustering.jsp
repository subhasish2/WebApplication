<%@page import="com.Clustering.Location"%>
<%@page import="com.Clustering.Cluster"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.Clustering.DBSCAN"%>
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
		String filename = "C:/Users/Subhasish/git/WebApplication/Login/csv/bike.csv";
		DBSCAN d = new DBSCAN(filename, 20, 2);
		String outfile = d.DBSCAN_Clustering();
		ArrayList<Location> centers = d.getCentroids();
		ArrayList<Cluster> clusters = d.getClusters();
		ArrayList<Location> noise= d.getNoise();
	%>
	<script>
		function initMap() {

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 12,
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
					map : map,
					icon: {
			            path: google.maps.SymbolPath.CIRCLE,
			            scale: 5
			          }
					
				});
			});
			var noise_marker = noise.map(function(location, i) {
				return new google.maps.Marker({
					position : location,
					//label : labels[i % labels.length],
					map : map,
					icon: {
			            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
			            scale: 5
			          }
					
				});
			});

			// Add a marker clusterer to manage the markers.
			// var markerCluster = new MarkerClusterer(map, markers,
			//  {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
			var circles = centers.map(function(center, i) {
				return new google.maps.Circle({
					center : center,
					radius : radius[i],
					strokeColor : "#0000FF",
					strokeOpacity : 0.3,
					strokeWeight : 1,
					fillColor : "#0000FF",
					fillOpacity : 0.4,
					map : map
				});
			});
			/*for(j=0;i<circles.length;j++) {
				circles[j].setRadius(radius[j]);
			}*/

		}

		var locations = [
	<%File file = new File(outfile);
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String header = scan.nextLine();
				String[] headpart = header.split(" ");
				String[] cl_info = headpart[1].split(":");
				int cl_size = Integer.parseInt(cl_info[1]);
				//String center = scan.nextLine();
				//centers.add(center.substring(0, center.length() - 1));
				//out.println(center);
				if (scan.hasNext())
					for (int i = 0; i < cl_size; i++)
						out.println(scan.nextLine());
			}%>
		]

		var centers = [
	<%for (int i = 0; i < centers.size() - 1; i++) {
				Location l = centers.get(i);
				out.println("{lat: " + l.getLatitude() + ", lng: " + l.getLongitude() + "},");
			}
			Location l = centers.get(centers.size() - 1);
			out.println("{lat: " + l.getLatitude() + ", lng: " + l.getLongitude() + "}");%>
		]
		var radius = [
			<%for(int i=0;i<clusters.size();i++) {
				Cluster c=clusters.get(i);
				out.println(c.getRadius()+",");
			}
			out.println(clusters.get(clusters.size()-1).getRadius());
			//System.out.println(c.getRadius());
			%>
		]
		var noise = [
			<%
			if(noise!=null) {
				for(int i=0;i<noise.size();i++) {
					Location loc=noise.get(i);
					out.println("{lat: " + loc.getLatitude() + ", lng: " + loc.getLongitude() + "},");
				}
				Location loc = noise.get(noise.size() - 1);
				out.println("{lat: " + loc.getLatitude() + ", lng: " + loc.getLongitude() + "}");
			}
			%>
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