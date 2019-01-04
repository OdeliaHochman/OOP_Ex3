package File_Format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.io.File;
//import Algorithms.MultiCSV;
//import GIS.Meta_data;
import GIS.MyGIS_project;

public class CSVReader {

//    public static void main(String[] args)
//    {
//    	String sDirName = "C:\\data\\";
//    	MultiCSV multCsv = new MultiCSV(sDirName);
//    	ArrayList<File> fl = multCsv.getFiles(sDirName);
//    	ArrayList<String[] > al = null;
//    	String [] csvFiles={"WigleWifi_20171201110209.csv"};
//    	String [] kmlFiles= new String[csvFiles.length];
//    	for (int idx=0;idx<csvFiles.length;idx++ ) {
//    		 String csvFile  = csvFiles[idx];
//    		 String kmlFile =  csvFile.split("[.]")[0]+".kml";
//    		 kmlFiles[idx] = kmlFile;
//    		 al = ReadFile(sDirName+csvFile);
//		}
//    
//    	//kmlFiles
//    	for (String sKmlFile : kmlFiles) {
//    		 
//        	csv2kml.writeFileKML(al,sDirName+sKmlFile);
//		}		
//    }
	
	public static void main(String[] args)
    {
		String dirPath ="C:\\dataGame";
		MyGIS_project gp = new MyGIS_project(dirPath);
		gp.WriteLayers2Kml();
    }
	
	public static ArrayList<String[] > ReadFile(String csvFileName)
    {
		ArrayList<String[]> m_arr =new ArrayList<>();
		
        String csvFile = csvFileName;//"output.csv";
        String line = "";
        String cvsSplitBy = ",";
        int count=0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
        	while ((line=(br.readLine()))!= null)
        	{
        	count++;
        	
            if(count>1) 
            {
                String[] userInfo = line.split(cvsSplitBy);
                m_arr.add(userInfo);

            }
        	}  

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return m_arr;
    }

}