package File_Format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class csvWriter 
{
	public static void main(String[] args)
	{
		String fileName = "game_1543684662657.csv";
		PrintWriter pw = null;
		
		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

	
   		
		StringBuilder sb = new StringBuilder();
		sb.append("\"<Placemark><name><![CDATA]\"");
		sb.append("]]></name><description><![CDATA]");
		sb.append("</b><br/>Frequency: <b>");
		sb.append("</b><br/>Date: <b>");
		sb.append("</b>]]></description><styleUrl>#");
		sb.append("</styleUrl><Point><coordinates>");
		sb.append("</coordinates></Point></Placemark>");
		sb.append('\n');

		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
}



