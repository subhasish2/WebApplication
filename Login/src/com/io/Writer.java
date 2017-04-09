package com.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.Clustering.Cluster;
import com.Clustering.Location;

public class Writer {
	FileWriter w1;
	FileWriter w2;

	public Writer(String filename1, String filename2) {
		try {
			w1 = new FileWriter(filename1);
			w2 = new FileWriter(filename2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(ArrayList<Cluster> clusters) {
		try {
			Location loc;
			Cluster cl;
			w1.write("No. of Cluster: " + clusters.size() + "\n");
			for (Cluster c : clusters) {
				w1.write("Cluster id:" + c.getId() + "\n");
				for (Location l : c.getPoints()) {
					w1.write(l.toString() + "\n");
				}
			}
			w1.close();
			for (int j = 0; j < clusters.size() - 1; j++) {
				cl = clusters.get(j);
				ArrayList<Location> points = new ArrayList<>(cl.getPoints());
				w2.write("Cluster_id:" + cl.getId() + " Elements:" + points.size() + "\n");
				for (int i = 0; i < points.size(); i++) {
					loc = points.get(i);
					w2.write("{lat: " + loc.getLatitude() + ", lng: " + loc.getLongitude() + "},\n");
				}
			}
			cl = clusters.get(clusters.size() - 1);
			ArrayList<Location> points = new ArrayList<>(cl.getPoints());
			w2.write("Cluster_id:" + cl.getId() + " Elements:" + points.size() + "\n");
			for (int i = 0; i < points.size() - 1; i++) {
				loc = points.get(i);
				w2.write("{lat: " + loc.getLatitude() + ", lng: " + loc.getLongitude() + "},\n");
			}
			loc = points.get(points.size() - 1);
			w2.write("{lat: " + loc.getLatitude() + ", lng: " + loc.getLongitude() + "}");
			w2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Writer wr = new Writer("Output/file_" + new Date().getTime() + ".txt",
				"Output/mapfile_" + new Date().getTime() + ".txt");
		// wr.write();

	}

}
