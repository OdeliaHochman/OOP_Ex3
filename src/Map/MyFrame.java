package Map;

import java.awt.Color;

import Game.Fruit;
import Game.Game;
import Game.Packman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.MessageContext.Scope;

import Algorithms.ShortestPathAlgo;
import Game.Game;


/**
 * A graphical class that allows robots and fruits to be displayed on the map,
 * displaying the activity of algorithms, saving data, and performing a reconstruction of data from csv files or creating a game by selecting robots and fruits and positioning them on the map.
 * @author Efrat Cohen and Odelia Hochman
 *
 */

class PacmanTimer
{ 
	private static JFileChooser inputFile;
	MyFrame m_form  = null;
	PacmanTimer(MyFrame form){m_form = form;}
	Timer timer=null;//new Timer();


	public void Start(int pcID, int period) {
		// TODO Auto-generated method stub
		timer=new Timer();
		timer.scheduleAtFixedRate( new PacmanDoTask(m_form,pcID), (long)100* period, (long)200 * period /** 60 * 60 * period*/); 
	}
	public void Stop() 
	{
		timer.cancel();
	}
	
}   

class PacmanDoTask extends TimerTask {
	MyFrame m_form  = null;
	int m_pcID = -1;
	PacmanDoTask(MyFrame form,int pcID){m_form = form;m_pcID = pcID;}
	public void run() 
	{
		System.out.println("PC ID "+ m_pcID);
		m_form.Repaint();
		//code to send SMS.
	}
}

public class MyFrame extends JFrame implements MouseListener
{
	long gameTimeSec = 0;
	MyMap map= new MyMap();
	Game game= new Game();
	public BufferedImage myImage;
	ShortestPathAlgo pathManager;
	PacmanTimer pacsTimer = new PacmanTimer(this);
	ArrayList<Point>pnts = new ArrayList<>();
	Image packmanIcon;
	BufferedImage packman;
	private JTextField field;


	public MyFrame() 
	{
		
		initGUI();		
		this.addMouseListener(this);

	}
	public void Repaint() {
	
		gameTimeSec++;
		boolean ans = pathManager.UpdateGameState(gameTimeSec);
		if(ans == false)
			pacsTimer.Stop();
		repaint();
	
	}



	private void DrawPoints(Graphics g)
	{
		g.drawImage(map.GetImage(), 0, 0, this);
		g.drawImage(packman, 10, 10, this);
		for (Point point : pnts) {
			if(x!=-1 && y!=-1)
			{
				int r =30;
				g.fillOval(point.x, point.y, r, r);
			}
		}

	}
	private void initGUI() 
	{
	
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		Menu menu2= new Menu("File");
		MenuItem itemRun = new MenuItem("Game Run");
		MenuItem itemStop = new MenuItem("Game Stop");
		MenuItem itemPackman=new MenuItem("Add Packman");
		MenuItem itemFruit=new MenuItem("Add Fruit");
		MenuItem itemClear=new MenuItem("clear screen");
		MenuItem itemFile= new MenuItem("Load File");
		MenuItem itemSave= new MenuItem("Save Game");
		
		
//		ActionListener argClear = new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//
//				game.clearGame();
//				this.removeMouseListener(argPackman);
//				this.removeMouseListener(argFruit);
//				
//				repaint();
//			}
//		};
		
		ActionListener argFile = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION || true)
			
				{
					String csvFileName = chooser.getSelectedFile().getPath();
					game.LoadCsv(csvFileName);
				    //System.out.println("You chose to open file: " + chooser.getSelectedFile().getName());
                    repaint();
                    pathManager= new ShortestPathAlgo(game);
                    pathManager.InitShortestPathAlgo();
                    
    				if(returnVal == JFileChooser.CANCEL_OPTION)
    				{
    					field.setText("cancel");
    				}
                
                   
				}
				
			}
		};
		
//		ActionListener argSave = new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e) 
//			{
//
//				JFileChooser chooser = new JFileChooser();
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
//				chooser.setFileFilter(filter);
//				int returnVal = chooser.showOpenDialog(null);
//				if(returnVal == JFileChooser.APPROVE_OPTION || true)
//				{
//					String saveFileName = chooser.getSelectedFile().getPath();
//					if(saveFileName.endsWith(".csv"))
//					{
//						CsvWriter csvWriter = new CsvWriter(saveFileName);
//					}
//					else if(saveFileName.endsWith(".kml"))
//					{
//						Path2Kml path2Kml= new Path2Kml(saveFileName);
//					}
//					else
//					{
//						field.setText("File extension must be csv or kml."); ////???
//					}
//
//					pathManager= new ShortestPathAlgo(game);
//					pathManager.InitShortestPathAlgo();
//
//				}
//				if(returnVal == JFileChooser.CANCEL_OPTION)
//				{
//					field.setText("cancel");
//				}
//
//
//			}
//		};
		ActionListener argRun = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a = 0;
				a++;
				System.out.println("menue a Clicked");
				x = 500;
				y = 500;
				pacsTimer.Start(1, 2);
				
				

			}
		};
		ActionListener argStop = new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int b = 0;
				b++;
				System.out.println("menue b Clicked");
				//timer.purge();
				pacsTimer.Stop();
			}
		};
		itemRun.addActionListener(argRun);
		itemStop.addActionListener(argStop);
		itemPackman.addActionListener(argPackman);
//		itemFruit.addActionListener(argFruit);
//		itemSave.addActionListener(argSave);
		itemFile.addActionListener(argFile);
		menuBar.add(menu);
		menuBar.add(menu2);
		menu.add(itemRun);
		menu.add(itemStop);
		menu.add(itemClear);
		menu.add(itemPackman);
		menu.add(itemFruit);
		menu2.add(itemFile);
		menu2.add(itemSave);
		this.setMenuBar(menuBar);
	

		
	}

	int x = -1;
	int y = -1;

	public void DrawPackmans(Graphics g, ArrayList<Packman> packmanLst) 
	{
		Color clrOld = g.getColor();
		Color[] clrs =  new Color[] {Color.BLUE,Color.CYAN,Color.RED};
		int idxColor =0;
		for (Iterator<Packman> iterator = packmanLst.iterator(); iterator.hasNext();) {
			Packman packman = (Packman) iterator.next();
			Color color = (clrs[idxColor]);
			packman.Draw(g,color);
			idxColor++;
			idxColor%=3;
		}
		g.setColor(clrOld);
	}
	public void DrawFruits(Graphics g, ArrayList<Fruit> fruitLst) 
	{

		for (int idxFrut = 0; idxFrut < fruitLst.size(); idxFrut++) 
		{
			if(fruitLst.get(idxFrut)!=null)
			
			//fruit.Draw(g);
		   if(fruitLst.get(idxFrut).Getvisibal()==true) 
		   {
			fruitLst.get(idxFrut).Draw(g);
		   }		
			
		}
			
		}
	
	
	public void paint(Graphics g)
	{
		
		//DrawPC(g,m_pcID);
		//DrawPoints(g);
		g.drawImage(map.GetImage(), 0, 0, this);
		//g.drawImage(myImage, 0, 0, this);
//		if(x!=-1 && y!=-1)
//		{
//			int r = 10;
//			x = x - (r / 2);
//			y = y - (r / 2);
//			g.fillOval(x, y, r, r);
//		}
		ArrayList<Packman> packmanLst= game.getArrListPac();
		if(packmanLst!=null) 
		DrawPackmans(g,packmanLst);
		ArrayList<Fruit> fruitLst= game.getArrListFruit();	
		if(fruitLst!=null)
		DrawFruits(g,fruitLst);
		
	}
	
	ActionListener argPackman=new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{

//			File f=new File("C:\\pacman.png");
//			try {
//		      	 packman = ImageIO.read(f);
//				packman.getScaledInstance(10, 10, 10);
//			} catch (IOException e1) {
//				
//				e1.printStackTrace();
//			}
				
			
		}
	
		public void Pacman(Graphics g)
		{
			//DrawPC(g,m_pcID);
			
			if(x!=-1 && y!=-1)
			{
				int r = 10;
				x = x - (r / 2);
				y = y - (r / 2);
				g.fillOval(x, y, r, r);
			}
	
		}
	
		
	};	
		
		
		public static void main(String[] args)
		{
			
			MyFrame window = new MyFrame();
			window.setVisible(true);
			window.setSize(window.map.GetImage().getWidth(),window.map.GetImage().getHeight());
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
	
	@Override
	public void mouseClicked(MouseEvent arg) 
	{
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();

		repaint();
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		 

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	
	}
