package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Map.MyMap;
import Geom.Point3D;
import java.awt.Point;

/**
 * A class that represents a "target" in a known geographic location without movement
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Fruit extends MapObject {
	
	
	private int weight;
	private boolean visible=true;
	public BufferedImage fruitImage;
	

	/**
	 * init
	 * @param location
	 * @param id
	 * @param weight
	 */
	
	public Fruit(Fruit frt) 
	{
	     this(frt.GetPointlocation(),frt.GetId(),frt.GetWeight());
	}

	public Fruit(Point pnt, int id, int weight) 
	{
		this.SetId(id);
		this.SetPointLocation(pnt);
		this.SetWeight(weight);
		
	}
	
	public Fruit( int id, int weight, Point3D pnt) 
	{
		this.SetId(id);
		this.SetWeight(weight);
		this.SetPoint3DLocation(pnt);
	}
	
	
//	public Fruit() //Point locationP, int id, int weight
//	{
//		this.SetId(id);
//		this.SetLocationP(locationP);
//		this.SetWeight(weight);
//		this.SetLocation(location);
//	}


	public boolean Getvisibal() 
	{
		return this.visible;
	}
	
	public void SetVisible(boolean visible) 
	{
		 this.visible=visible;
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
		String s= "Fruit:\n location="+ "x: "+ super.GetPointlocation().x+"y: "+ super.GetPointlocation().y + ",id=" + super.GetId() +" ,weight="+ weight; 
		return s;
	}
	
	
	   public void Draw(Graphics g) 
	    {
	    	g.setColor(Color.green);

			g.fillOval(super.GetPointlocation().x, super.GetPointlocation().y,15, 15);
			
			
	   
	   
	    }
	   
}
