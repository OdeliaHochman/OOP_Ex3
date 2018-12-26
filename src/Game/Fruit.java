package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import Map.MyMap;
import Geom.Point3D;
import java.awt.Point;

/**
 * A class that represents a "target" in a known geographic location without movement
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Fruit extends MapObject {
	
	private Point3D location;
	private Point locationP;
	private int id;
	private int weight;
	

	/**
	 * init
	 * @param location
	 * @param id
	 * @param weight
	 */
	public Fruit(Point3D location, int id, int weight) 
	{
		this.SetId(id);
		this.SetLocation(location);
		this.SetWeight(weight);
	}
	
	public Fruit(Point locationP, int id, int weight) 
	{
		this.SetId(id);
		this.SetLocationP(locationP);
		this.SetWeight(weight);
	}

	public Fruit() //Point locationP, int id, int weight
	{
		this.SetId(id);
		this.SetLocationP(locationP);
		this.SetWeight(weight);
	}


	public void SetLocationP(Point locationP) 
	{
		this.locationP=locationP;
	}
	
	public Point getlocationP() 
	{
		return getlocationP();
	}
	////
	public void SetLocation(Point3D location) 
	{
		this.location=location;
	}
	
	public Point3D getlocation() 
	{
		return location;
	}
	
	public void SetId(int id) 
	{
		this.id=id;
	}
	
	public int GetId() 
	{
		return id;
	}
   
	public void SetWeight(int weight) 
	{
		this.weight=weight;
	}
	
	public int GetWeight() 
	{
		return weight;
	}
	
	public String toString() 
	{
		String s= "Fruit:\n location="+ location + ",id=" + id +" ,weight="+ weight; 
		return s;
	}
	
	   public void Draw(Graphics g) 
	    {
	    	g.setColor(Color.green);
	    	//Point pixel=Map.getPositionOnScreen(this.location.x(), this.location.y())
			g.fillOval(this.locationP.x, this.locationP.y,10, 10);

	    }
}
