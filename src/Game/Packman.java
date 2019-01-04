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

	
	private int speed;
	private int radius;
	private double timeInPath;
	private int pathIdx =0;

	/**
	 * init
	 * @param locationPoint
	 * @param speed
	 * @param radius
	 * @param id
	 */
	public Packman(Packman pc)
	{
		this(pc.GetPoint3Dlocation(),pc.getSpeed(),pc.getRadius(), pc.GetId());
	}
	public Packman(Point3D pnt,int speed,int radius, int id)
	{
		super.SetPoint3DLocation(pnt);
		super.SetId(id);
		this.setSpeed(speed);
		this.setRadius(radius);
		
		
	}
	
	public int GetCurrentPathIndex()
	{
		return pathIdx;
	}
	public int IncreasePathIndex()
	{
		return pathIdx++;
	}
	
	public Packman(Point pnt,int speed,int radius,int id)
	{
		super.SetPointLocation(pnt);
		super.SetId(id);
		this.setSpeed(speed);
		this.setRadius(radius);
		

		
	}
	public Packman()
	{
//		super.setLocation(super.location);
//		this.setSpeed(speed);
//		this.setRadius(radius);	
//		this.setId(id);
	}
	
	public int getSpeed() {
		return speed;
	}

	

	public void setSpeed(int speed) {
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
		return "Packman id=" + super.GetId() + ", location=" + "x:"+ super.GetPointlocation().x + "y:"+ super.GetPointlocation().y + ", speed=" + speed + ", radius="+ radius;
	}
	
	public String[] ToStringArr() 
	{
		// TODO Auto-generated method stub
		char type='P';
		int alt=0;
		String [] sPacArr=new String [] {String.valueOf(type),String.valueOf(super.GetId()),String.valueOf(super.GetPoint3Dlocation().y()) ,String.valueOf(super.GetPoint3Dlocation().x()) ,String.valueOf(alt),String.valueOf(speed), String.valueOf(radius)};
		return sPacArr;
	}
	

	MyMap map= new MyMap();
	Image packmenIcon;          
      public void Draw(Graphics g,Color color) 
    {
    	   	g.setColor(/*Color.red*/color);
        	
     
      //	packmenIcon = Toolkit.getDefaultToolkit().getImage("C:\\pacman.png");
      //	g.drawImage(packmenIcon,x_pacman, y_pacman, 28, 28,this);
      	 
      	//Point pixel=Map.getPositionOnScreen(this.locationPoint.x(), this.locationPoint.y());
  		g.fillOval(super.GetPointlocation().x,super.GetPointlocation().y,20,20);
        
    }
}

