package com.io;

import java.sql.Timestamp;

public class TimeParser {
	String s;
	Timestamp t;
	public TimeParser(){
		
	}
	public Timestamp parseTime(String time) {
		String[] datetime;
		s=new String(time);
		datetime=s.split(" ");
		String[] date=datetime[0].split("-");
		s=date[2]+"-"+date[1]+"-"+date[0];
		/*if(datetime[1].endsWith("AM")) {
            String[] t=datetime[1].substring(0, 8).split(":");
            if(t[0].compareTo("12")==0) {
                t[0]="00";
                s+=" "+t[0]+":"+t[1]+":"+t[2];
            }
            else
                s+=" "+datetime[1].substring(0, 8);
        }
        else {
            String[] t=datetime[1].split(":");
            if(t[0].compareTo("12")!=0) {
                int ti=Integer.parseInt(t[0])+12;
                t[0]=String.valueOf(ti);
            }
            s+=" "+t[0]+":"+t[1]+":"+t[2];
        }*/
		datetime[1]+=":00";
		s+=" "+datetime[1];
		t=Timestamp.valueOf(s);
		return t;
		
	}
	public void show(){
		System.out.println(t.getTime()+" "+t.toString());
		
	}
	public static void main(String[] args) {
		TimeParser p=new TimeParser();
		System.out.println(p.parseTime("22-02-2017  05:00"));
		p.show();
	}
}
