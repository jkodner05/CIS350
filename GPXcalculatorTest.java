package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import org.junit.Assert.*;
import junit.framework.TestCase;


public class GPXcalculatorTest
{
	//tests a regular GPXtrk
	@Test
	public void testRegular()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(10,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(calcDist,actualDist,0.00000000001);
	}
	
	//tests a regular GPXtrk with negative values
	@Test
	public void testRegularNeg()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(-10,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(calcDist,actualDist,0.00000000001);
	}
	
	//GPXtrk is null
	@Test
	public void testGPXtrkNull()
	{
		GPXtrk track = null;
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(-1,calcDist,0.00000000001);
	}
	
	//GPXtrk empty
	@Test
	public void testGPXtrkEmpty()
	{
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(-1,calcDist,0.00000000001);
	}
	
	//one GPXtrkseg, is null
	@Test
	public void testGPXtrksegNull()
	{
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(null);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//multiple GPXtrkseg, one is null
	@Test
	public void testGPXtrksegOneNull()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(100,0, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(null);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//one GPXtrkseg, is empty
	@Test
	public void testGPXtrksegEmpty()
	{
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(new GPXtrkseg(new ArrayList<GPXtrkpt>()));
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//multiple GPXtrkseg, one is empty
	@Test
	public void testGPXtrksegOneEmpty()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(100,0, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(new GPXtrkseg(new ArrayList<GPXtrkpt>()));
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a single-point GPXtrkseg with other GPXtrkseg GPXtrk 
	@Test
	public void testOneSinglePt()
	{
		GPXtrkpt p2 = new GPXtrkpt(10,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a single-point GPXtrkseg
	@Test
	public void testOneSinglePtOnly()
	{
		GPXtrkpt p2 = new GPXtrkpt(100,0, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		pointList1.add(p2);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a null-point GPXtrkseg with other GPXtrkseg GPXtrk 
	@Test
	public void testOneNullPt()
	{
		GPXtrkpt p1 = null;
		GPXtrkpt p2 = new GPXtrkpt(10,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a single-point GPXtrkseg
	@Test
	public void testOneNullPtOnly()
	{
		GPXtrkpt p2 = null;
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		pointList1.add(p2);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a Invalid Latitute Positive
	@Test
	public void testLatPos()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(100,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a Invalid Latitute Negative
	@Test
	public void testLatNeg()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(-100,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a Invalid Longitude Positive
	@Test
	public void testLonPos()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(10,181, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,10, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(0,calcDist,0.00000000001);
	}
	
	//tests a Invalid Longitude Negative
	@Test
	public void testLonNeg()
	{
		GPXtrkpt p1 = new GPXtrkpt(0,0, new Date());
		GPXtrkpt p2 = new GPXtrkpt(-10,0, new Date());
		GPXtrkpt p3 = new GPXtrkpt(90,-181, new Date());
		GPXtrkpt p4 = new GPXtrkpt(50,50, new Date());
		GPXtrkpt p5 = new GPXtrkpt(50.1,50.1, new Date());
		ArrayList<GPXtrkpt> pointList1 = new ArrayList<GPXtrkpt>();
		ArrayList<GPXtrkpt> pointList2 = new ArrayList<GPXtrkpt>();
		pointList1.add(p1);
		pointList1.add(p2);
		pointList2.add(p3);
		pointList2.add(p4);
		pointList2.add(p5);
		GPXtrkseg ts1 = new GPXtrkseg(pointList1);
		GPXtrkseg ts2 = new GPXtrkseg(pointList2);
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(ts1);
		segs.add(ts2);
		GPXtrk track = new GPXtrk("Test Track", segs);
		
		double calcDist = GPXcalculator.calculateDistanceTraveled(track);
		double actualDist = Math.sqrt(Math.pow((90-50),2)+Math.pow((10-50),2)) 
							+ Math.sqrt(2*Math.pow(.1,2));
		assertEquals(0,calcDist,0.00000000001);
	}
}
