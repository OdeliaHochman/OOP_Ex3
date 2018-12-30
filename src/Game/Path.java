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
	private int idPackman =-1;
	private int idFruit =-1;
	


	/**
	 * init
	 */
	public Path(int idPc,int idFrt, double DeltaTime)/*double time0,double timeT*/ 
	{

		idPackman = idPc;
		idFruit = idFrt;
		this.DeltaTime = DeltaTime;
		//this.time0 = time0;
		this.timeT = DeltaTime-time0;
		
	}
	
    public int GetIDPackman() 
    {
    	return idPackman;
    }

    public int GetIDFruit() 
    {
    	return idFruit;
    }
    
   public double GetTimeT() 
   {
	   return timeT;
   }

   public double GetTime0() 
   {
	   return time0;
   }
   public void SetTimeT(double timeT) 
   {
	   this.timeT=timeT;
   }

   public void SetTime0(double time0) 
   {
	   this.time0= time0;
   }


   public double GetDeltatime() 
   {
	   return DeltaTime;
   }

	public String toString()
	{
		return "Path:\n packman ID: " + idPackman +"Fruit ID: "+ idFruit+", time total:" + timeT + ", Packman start time:" + time0 +"delta time:"+DeltaTime;
	}

}
