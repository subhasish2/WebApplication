package com.Clustering;

import java.sql.Timestamp;

public class Location {
	Timestamp timestamp;
	String id;
	double longitude, latitude;

	public Location(String id, double latitude, double longitude, Timestamp timestamp) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
	}
	public Location(String id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Location(Location l) {
		super();
		this.timestamp = l.timestamp;
		this.id = l.id;
		this.longitude = l.longitude;
		this.latitude = l.latitude;
	}


	// return distance between this location and that location
	// measured in statute miles
	public double distanceTo(Location that) {
		double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
		double lat1 = Math.toRadians(this.latitude);
		double lon1 = Math.toRadians(this.longitude);
		double lat2 = Math.toRadians(that.latitude);
		double lon2 = Math.toRadians(that.longitude);

		// great circle distance in radians, using law of cosines formula
		double angle = Math
				.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		// each degree on a great circle of Earth is 60 nautical miles
		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		double km, meter;
		km = 6371 * angle;
		meter = km * 1000;
		return meter;

	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	// return string representation of this point
	public String toString() {
		return id + " (" + latitude + ", " + longitude + ") " + timestamp;
	}

}
