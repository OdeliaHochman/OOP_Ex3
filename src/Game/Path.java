package Game;

import java.awt.Point;
import java.util.ArrayList;

import Algorithms.ShortestPathAlgo;

//import java.util.Timer;
//import Coords.MyCoords;
//import java.awt.Point;
//import Map.Map;


/**
 * The department represents a path made up of a GPS point collection.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Path {


	ArrayList<Fruit> fruitList = new ArrayList<>();
	private double time0 = 0;
	private double timeT = -1;
	private double DeltaTime = -1;
	private int idxPackman =-1;
	private int idxFruit =-1;
	


	/**
	 * init
	 */
	public Path(int idxPc,int idxFrt, double DeltaTime)/*double time0,double timeT*/ 
	{

		idxPackman = idxPc;
		idxFruit = idxFrt;
		this.DeltaTime = DeltaTime;
		//this.time0 = time0;
		//this.timeT = timeT;
		
	}
	
    public int getidxPackman() 
    {
    	return idxPackman;
    }

    public int getidxFruit() 
    {
    	return idxFruit;
    }
    
   public double getTimeT() 
   {
	   return timeT;
   }

   public double getTime0() 
   {
	   return time0;
   }
   public void setTimeT(double timeT) 
   {
	   this.timeT=timeT;
   }

   public void setTime0(double time0) 
   {
	   this.time0= time0;
   }


   public double getDeltatime() 
   {
	   return DeltaTime;
   }

	public String toString()
	{
		return "Path:\n packman ID: " + idxPackman +"Fruit ID: "+ idxFruit+", time total:" + timeT + ", Packman start time:" + time0 +"delta time:"+DeltaTime;
	}

}
