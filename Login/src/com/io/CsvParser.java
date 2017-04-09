package com.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import com.Clustering.Location;

public class CsvParser {
	ArrayList<Location> dataList;
	String filename;
	File file;
	private static Scanner scan, wholeScan;
	private Scanner nextScan, firstScan;
	private TimeParser timeparser;

	public CsvParser(String filename) {
		// dataList = new LinkedList<>();
		this.filename = filename;
		file = new File(filename);
		timeparser = new TimeParser();
		try {
			nextScan = new Scanner(file);
			scan = new Scanner(file);
			scan.nextLine();
			scan.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Location> readData() {
		dataList = new ArrayList<>();
		Location loc;
		try {
			wholeScan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String row = wholeScan.nextLine();
		// System.out.println(row);
		String[] values = row.split(",");
		// System.out.println(values.length);
		while (wholeScan.hasNext()) {
			row = wholeScan.nextLine();
			values = row.split(",");
			loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
					timeparser.parseTime(values[3]));
			dataList.add(loc);
			// System.out.println(row);
		}
		/*
		 * for (Location l : dataList) System.out.println(l);
		 */
		return dataList;
	}

	public ArrayList<Location> readData_next(Timestamp timestamp) {
		dataList = new ArrayList<>();
		Location loc;
		boolean flag = false;
		/*
		 * try { scan = new Scanner(file); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 */
		String row = scan.nextLine();
		// System.out.println(row);
		// row = scan.nextLine();
		String[] values = row.split(",");
		// System.out.println(values.length);
		while (scan.hasNext()) {
			// nextScan = (Scanner) scan.clone();
			row = scan.nextLine();
			values = row.split(",");
			if (timeparser.parseTime(values[3]).getTime() != timestamp.getTime()) {
				flag = true;
				scan = nextScan;
				break;

			}
			loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
					timeparser.parseTime(values[3]));
			dataList.add(loc);

			// System.out.println(row);
		}
		if (flag == false)
			nextScan = scan;
		/*
		 * for (Location l : dataList) System.out.println(l);
		 */

		return dataList;
	}

	public ArrayList<Location> readData(Location l) {
		dataList = new ArrayList<>();
		Location loc;
		dataList.add(l);
		String row;
		String[] values;
		while (scan.hasNext()) {
			row = scan.nextLine();
			values = row.split(",");
			if (timeparser.parseTime(values[3]).getTime() != l.getTimestamp().getTime()) {
				loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
						timeparser.parseTime(values[3]));
				dataList.add(loc);
				return dataList;
			}
			loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
					timeparser.parseTime(values[3]));
			dataList.add(loc);
		}
		return dataList;
	}

	public Location readNextRecord() {
		Location loc;
		if (nextScan.hasNext()) {
			String row = nextScan.nextLine();
			String[] values = row.split(",");
			loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
					timeparser.parseTime(values[3]));
			return loc;
		} else
			return null;
	}

	public Location readFirstRecord() {
		Location loc;
		try {
			firstScan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		firstScan.nextLine();
		String row = firstScan.nextLine();
		String[] values = row.split(",");
		loc = new Location(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
				timeparser.parseTime(values[3]));
		nextScan = firstScan;
		return loc;
	}

}
