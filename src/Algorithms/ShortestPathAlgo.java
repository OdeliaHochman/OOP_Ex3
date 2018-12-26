package Algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

import Coords.MyCoords;
import Coords.coords_converter;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Game.Path;
import Game.Solution;
import Map.MyMap;

import java.awt.Point;
//Path Manager
/**
 * A department that receives a collection of robots and fruits and computers
 *The optimal route so that all the fruits will be "eaten" as quickly as possible -
 *this is the central algorithmic department and includes the calculation of "fruit tracks" for each of the producers.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class ShortestPathAlgo {

	private Game game;
	private Path path;
	private double speed;
	private long gameTimeSec;
	ArrayList<Packman> packmanList;
	ArrayList<Fruit>fruitList;
	private HashSet<Integer> hashAvailableFrt = new HashSet<>();
	double timeTotal = path.getTime0()+path.getDeltatime();
	Solution allPath = new Solution();


	
	
	public ShortestPathAlgo(Game game) 
	{
		this.game=game;
		this.fruitList=game.getArrListFruit();
		this.packmanList=game.getArrListPac();
		for (int index =0; index< fruitList.size();index++) {
		//Fruit fruit = (Fruit) iterator.next();
	    //Fruit f = fruitList.get(index);
		hashAvailableFrt.add(index);
//		if(hashAvailableFrt.contains(0)) {}
		}
		
	};

	public ShortestPathAlgo()
	{
		gameTimeSec =0;
		setSpeed(speed);
		setTime(gameTimeSec);
		this.fruitList=game.getArrListFruit();
		this.packmanList=game.getArrListPac();
		
		while(hashAvailableFrt.size()>0) 
		{
			if(gameTimeSec == 0)
				InitPathes();
			for (int idx = 0; idx < packmanList.size(); idx++) 
			{
				boolean ans= IsPackmanReachToFruit(getTime(),timeTotal);
				if(ans==false) 
				{
					hashAvailableFrt.remove(fruitList.get(idx));
					pathPacToOneFruit(packmanList.get(idx));	
				}
				
				else
					
					gameTimeSec++;

			}
		}
	
	}

	/**
	 * Route allocation
	 */
	private void InitPathes() 
	{
		for (int idx = 0; idx < this.packmanList.size(); idx++)
		{
			pathPacToOneFruit(this.packmanList.get(idx));
		}
	
	}


	/**
	 * The function calculates the distance between the fruit and the packman
	 * @param p-packman
	 * @param f-fruit
	 * @return distance
	 */
	public static double distanceInTimeP2F(Packman p, Fruit f)
	{
		double distance=0.0;
		Point pointPc=p.getLocation();
		Point pointFr=f.getlocationP();
		distance=MyMap.DisBetweenPixels(pointPc, pointFr);
		double time= distance/p.getSpeed();
		
		return time;

	}
	/**
	 * The function calculates one Pacman path to one fruit
	 * @param p-packman
	 */
	public void pathPacToOneFruit(Packman p) 
	{
	   double[] pathFun= ShortestPathAlgo.findClosestFruit(p, fruitList);
	   int idPackman=(int)pathFun[0];
	   int idxFruit=(int)pathFun[1];
	   double theShortsTime =pathFun[2];
	   Path path = new Path(idPackman,idxFruit,theShortsTime);
	   allPath.add(path);
	  
	}
//selectpathdata
	/**
	 * the function find the closest fruit to the packman
	 * @param p-packman
	 * @param fruit - arraylist of fruit
	 * @return array[id of packman , id of fruit, the shortest time]
	 */
   public static double[] findClosestFruit(Packman p, ArrayList<Fruit> fruit) 
   {
	  
	   ArrayList<Double> arrTimeOfPc= new ArrayList<>();
	   for (Iterator<Fruit> iterator = fruit.iterator(); 
			   iterator.hasNext();) 
	   {
		Fruit fr = (Fruit) iterator.next();
		double timeFP=distanceInTimeP2F(p, fr);
		arrTimeOfPc.add(timeFP);
	  }
	   double theShortsTime=arrTimeOfPc.get(0);
	   int idxFruit=0;
	   for(int nextTime=1; nextTime<arrTimeOfPc.size(); nextTime++) 
	   {
		   if(arrTimeOfPc.get(nextTime)<theShortsTime) 
		   {
			   theShortsTime=arrTimeOfPc.get(nextTime);
			   idxFruit=nextTime;
		   }
		   
	    }
	   double[] pathData= {(int)p.getId(), (int)idxFruit, theShortsTime};
	   
	   return pathData;

	}
   
   /**
    * The function updates the Pacman position by time
    * @param path
    * @param pc-packman
    * @param frt-fruit
    * @param time
    */
	public void setPositionInTime(Path path,Packman pc, Fruit frt,double time)
	{
		double proportionTime=(time-path.getTime0())/(path.getDeltatime()) ;
		double dx=Math.abs(frt.getlocation().x()-pc.getLocationPoint().x());
		double dy=Math.abs(frt.getlocation().y()-pc.getLocationPoint().y());
		double x=pc.getLocationPoint().x()+(proportionTime* dx);
		double y=pc.getLocationPoint().y()+(proportionTime* dy);
		 Point newPackman= new Point(MyMap.getPositionOnScreen(x, y));
		 pc.setLocation(newPackman);
		 
	}
	
 /**
  * The function checks whether the Pacman has reached fruit
  * @param time
  * @param timeTotal
  * @return true if the packman reach the fruit
  */
	public boolean IsPackmanReachToFruit(double time, double timeTotal)
	{
		if(time<timeTotal) 
		
			return false;
			
			else
				return true;
	}

	
	
	
	public Game getGame() {
		return game;
	}

	public Path getPath() {
		return path;
	}


	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public long getTime() {
		return getTime();
	}

	public void setTime(long gameTimeSec) {
		this.gameTimeSec = gameTimeSec;
	}
}
