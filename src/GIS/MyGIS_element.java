package GIS;

import Geom.Geom_element;
import GIS.Meta_data;
import Geom.Point3D;
import Coords.MyCoords;
import Coords.coords_converter;
import Geom.Geom_element;

import java.sql.Timestamp;

import javax.xml.crypto.Data;
import File_Format.CSVReader;
import Algorithms.MultiCSV;


/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Efrat cohen and Odelia Hochman
 *
 */
public class MyGIS_element implements GIS_element {
	
	private Point3D geom;
	private MyMeta_data data;

	public String[] ToStringArr()
	{
		String[] arrGisStrs = new String[9];

			arrGisStrs[0]=this.data.getType();
			arrGisStrs[1]=this.data.getId();
			arrGisStrs[2]= Double.toString(this.geom.x()) ;
		    arrGisStrs[3]= Double.toString(this.geom.y()) ;
		    arrGisStrs[4]= Double.toString(this.geom.z()) ;
		    arrGisStrs[5]=this.data.getSpeedWeight();
		    arrGisStrs[6]=this.data.getRaduis();
		    
		return arrGisStrs;
	}
	
	
	public MyGIS_element(String [] stringsOfGisElem) 
	{
		
	
		   this.data = new MyMeta_data(stringsOfGisElem);
		  
		   this.geom = new Point3D(stringsOfGisElem);	 
	   
	}

	
	@Override
	public Geom_element getGeom()
	{
		
		return this.geom;
	}

	@Override
	public Meta_data getData() 
	{
		return this.data;
	}
	
	
	@Override
	public void translate(Point3D vec)
	{
		geom=(new MyCoords().add(geom,vec));
	}

}
