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
import File_Format.csvWriter;

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
    	boolean bIsTitle=true;
   	 ArrayList<String[]> alCsvData = CSVReader.ReadFile(csvFileName);
   	 for (Iterator<String[]> iterator = alCsvData.iterator(); iterator.hasNext();) 
   	 {
   		 if(!bIsTitle)
   		 {
			String[] strings = (String[]) iterator.next();
			AddGameItem(strings);	
		}
   		 
   		 else
   			 
   			 bIsTitle=false;
   	 }
     }
    
    public void UpLoadCsv(String csvFileName) 
	{
		ArrayList<String[]> al = BuidPackAndFruitList(packmansOnMap,fruitsOnMap);
		SaveCsv(al,csvFileName);
		//ReadCSV(str);
	}
    
    public ArrayList<String[]> BuidPackAndFruitList(ArrayList<Packman> packmansOnMap,ArrayList<Fruit>fruitsOnMap)
	{
		ArrayList<String[]> alPackAndFruit= new ArrayList<>();
		
		for (Packman pac : packmansOnMap) 
		{
			
			alPackAndFruit.add(pac.ToStringArr());
		}
		
		for (Fruit frt : fruitsOnMap) 
		{
			alPackAndFruit.add(frt.ToStringArr());
		}
		
		
		return alPackAndFruit;
	}
    
    void SaveCsv(ArrayList<String[]> al,String csvFileName)
    {
    	csvWriter.WriteFile(csvFileName, al);
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
		if(packmansOnMap == null)
			packmansOnMap = new ArrayList<>();
		packmansOnMap.add(packman);
	}

	/**
	 * The function adds fruit to the fruit collection
	 * @param fruit
	 */
	public void AddFruit(Fruit fruit) 
	{
		if(fruitsOnMap == null)
			fruitsOnMap = new ArrayList<>();
		fruitsOnMap.add(fruit);
	}
	
	
	public ArrayList<Packman> GetArrListPac() 
	{
		return this.packmansOnMap;
	}
	
	public ArrayList<Fruit> GetArrListFruit() 
	{
		return this.fruitsOnMap;
	}
	
	public int GetSizeOfArrPackman() 
	{
		return packmansOnMap.size();
	}
	
	public int GetSizeOfArrFruit() 
	{
		return fruitsOnMap.size();
	}
	
//	public static void main(String[] args) 
//	{
//		Game game= new Game();
//	
//	}
//	
	public void RemoveFruit(Fruit f)
	{
		fruitsOnMap.remove(f);
	}
	public void RemovePackman(Packman p)
	{
		packmansOnMap.remove(p);
	}
	
	public void ClearGame()
	{
		this.packmansOnMap.clear();
		this.fruitsOnMap.clear();
	}

}

