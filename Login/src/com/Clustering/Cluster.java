package com.Clustering;

import java.util.LinkedList;

public class Cluster {
	int id;
	LinkedList<Location> points;

	public Cluster(int id) {
		this.id = id;
		points = new LinkedList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Location> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<Location> points) {
		this.points = points;
	}

	public void addLocation(Location l) {
		points.add(l);
	}

	public void showCluster() {
		System.out.println("Cluster id:" + id);
		for (Location l : points) {
			System.out.println(l);
		}
	}
	public Location getCentroid(){
		double x=0,y=0,z=0;
		for(Location l:points) {
			double lat = Math.toRadians(l.getLatitude());
			double lon = Math.toRadians(l.getLongitude());
			 x += Math.cos(lat) * Math.cos(lon);
	         y += Math.cos(lat) * Math.sin(lon);
	         z += Math.sin(lat);
		}
		int count=points.size();
		x/=count;
		y/=count;
		z/=count;
		Double centralLongitude = Math.atan2(y, x);
        Double centralSquareRoot = Math.sqrt(x * x + y * y);
        Double centralLatitude = Math.atan2(z, centralSquareRoot);
        Double latitude=Math.toDegrees(centralLatitude);
        Double longitude=Math.toDegrees(centralLongitude);
        Location center=new Location("", latitude, longitude);
        double min_distance=center.distanceTo(points.get(0));
        Location centroid=points.get(0);
        for(int i=1;i<points.size();i++) {
        	Location l=points.get(i);
        	Double distance=center.distanceTo(l);
        	if(distance<min_distance) {
        		min_distance=distance;
        		centroid=l;
        	}
        }
        return centroid;
	}
	
}
