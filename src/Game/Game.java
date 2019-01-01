package Game;

import Game.Packman;

import Game.Fruit;
import Geom.Point3D;
import Map.MyMap;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Iterator;

import File_Format.CSVReader;

/**
 * A class that includes a collection of fruit and a collection of robots, 
 * the class has the ability to build from the csv file and save its information to such a file.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Game 
{
	ArrayList<Packman> packmansOnMap;
	ArrayList<Fruit> fruitsOnMap;
	
	

    void ReadCSV(String csvFileName)
    {
   	 ArrayList<String[]> alCsvData = CSVReader.ReadFile(csvFileName);
   	 for (Iterator<String[]> iterator = alCsvData.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			AddGameItem(strings);
			
			
		}
   	 
   	 
    }
     void  AddGameItem(String[] objString)
     {

    		int id=Integer.parseInt(objString[1]);
    		int speed=Integer.parseInt(objString[5]);
    		int weight=Integer.parseInt(objString[5]);
    		double lat=Double.parseDouble(objString[2]);
    		double lon=Double.parseDouble(objString[3]);
    		double alt=Double.parseDouble(objString[4]);
    		Point pnt= MyMap.getPositionOnScreen(lat, lon);
    		//Point3D locationPoint= new Point3D(lat, lon, alt);
    		
		
    	 int idxID = 0;
    	 switch(objString[idxID])
    	 {
	    	 case "P":
	    	 {
	    		int raduis=Integer.parseInt(objString[6]);
	    		 Packman pc = new Packman(pnt,speed,raduis,id); //speed, radius, idxID);
	    		 packmansOnMap.add(pc) ;
	    		 break;
	    	 }
	    	 case "F":
	    	 {
	    		 Fruit ft =  new Fruit(pnt, id, weight);
	    		 fruitsOnMap.add(ft);
	    	 }
    	 }
     }
	/**
	 * init
	 */
	public Game() 
	{
		
	}
	
	public void LoadCsv(String csvFileName) 
	{
		packmansOnMap = new ArrayList<>();
		fruitsOnMap= new ArrayList<>();
		String str =csvFileName;//"C:\\dataGame\\game_1543684662657.csv";//csv file full path
		ReadCSV(str);
	}


	/**
	 * The function adds Packman to the Packman collection
	 * @param packman
	 */
	public void AddPackman(Packman packman) 
	{
		packmansOnMap.add(packman);
	}

	/**
	 * The function adds fruit to the fruit collection
	 * @param fruit
	 */
	public void AddFruit(Fruit fruit) 
	{
		fruitsOnMap.add(fruit);
	}
	
	
	public ArrayList<Packman> getArrListPac() 
	{
		return this.packmansOnMap;
	}
	
	public ArrayList<Fruit> getArrListFruit() 
	{
		return this.fruitsOnMap;
	}
	
	public int getSizeOfArrPackman() 
	{
		return packmansOnMap.size();
	}
	
	public int getSizeOfArrFruit() 
	{
		return fruitsOnMap.size();
	}
	

	public void removeFruit(Fruit f)
	{
		fruitsOnMap.remove(f);
	}
	public void removePackman(Packman p)
	{
		packmansOnMap.remove(p);
	}
	
		
	public void clearGame()
	{
		this.packmansOnMap.clear();
		this.fruitsOnMap.clear();
	}


}

