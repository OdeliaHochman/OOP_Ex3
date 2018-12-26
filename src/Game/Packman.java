package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Geom.Point3D;
import Map.MyMap;

/**
 * A class representing a "robot" with location, orientation and mobility
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Packman extends MapObject {

	private int id;
	private Point3D locationPoint;
	private Point location;
	private double speed;
	private int radius;
	private double timeInPath;

	/**
	 * init
	 * @param locationPoint
	 * @param speed
	 * @param radius
	 * @param id
	 */
	public Packman(Point3D locationPoint,double speed,int radius,int id)
	{
		this.setLocationPoint(locationPoint);
		this.setSpeed(speed);
		this.setRadius(radius);
		this.setId(id);
	}
	
	public Packman(Point location,double speed,int radius,int id)
	{
		this.setLocation(location);
		this.setSpeed(speed);
		this.setRadius(radius);	
		this.setId(id);
		
	}
	public Packman()
	{
		this.setLocation(location);
		this.setSpeed(speed);
		this.setRadius(radius);	
		this.setId(id);
	}




	public Point3D getLocationPoint() {
		return locationPoint;
	}
	
	public Point getLocation() 
	{
		return location;
	}

	public void setLocationPoint(Point3D locationPoint) {
		this.locationPoint = locationPoint;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public double getSpeed() {
		return speed;
	}

	public void setId(int id) 
	{
		this.id=id;
	}

	public int getId() 
	{
		return id;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) 
	{
		this.radius = radius;
	}


	public String toString()
	{
		return "Packman id=" + id + ", location=" + locationPoint + ", speed=" + speed + ", radius="+ radius;
	}

	MyMap map= new MyMap();
	Image packmenIcon;          
      public void Draw(Graphics g) 
    {
    	   	g.setColor(Color.red);
        	
     
      //	packmenIcon = Toolkit.getDefaultToolkit().getImage("C:\\pacman.png");
      //	g.drawImage(packmenIcon,x_pacman, y_pacman, 28, 28,this);
      	 
      	//Point pixel=Map.getPositionOnScreen(this.locationPoint.x(), this.locationPoint.y());
  		g.fillOval(this.location.x,this.location.y,20,20);
        
    }
}

