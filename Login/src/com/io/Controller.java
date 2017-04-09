package com.io;

import java.util.ArrayList;

import com.Clustering.DBSCAN;
import com.Clustering.Location;

public class Controller {
	Location loc;
	ArrayList<Location> datapoints;
	CsvParser csvParser;

	public Controller(String filename) {
		csvParser = new CsvParser(filename);
		loc = csvParser.readFirstRecord();
		datapoints = new ArrayList<>();
	}

	public void start() {
		// System.out.println(loc);
		// int i = 6;
		while (loc != null) {
			datapoints = csvParser.readData(loc);
			Location newloc = datapoints.get(datapoints.size() - 1);
			System.out.println("For time: " + loc.getTimestamp());
			if (newloc.getTimestamp().getTime() == loc.getTimestamp().getTime())
				loc = null;
			else {
				loc = new Location(newloc);
				datapoints.remove(datapoints.size() - 1);
			}

			// for(Location l:datapoints)
			// System.out.println(l);
			DBSCAN d = new DBSCAN(datapoints, 20, 2);
			d.DBSCAN_Clustering();

			// loc = csvParser.readNextRecord();
			// i--;
		}
		// System.err.println("Some problem Occurs!!");
		// System.err.println("Problem in readData method of the CsvParser
		// class!!");
	}

}
