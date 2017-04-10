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
	
}
