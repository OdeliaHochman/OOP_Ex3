package Map;

import java.awt.Color;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Game.Path;
import Geom.Point3D;
import Map.MyMap;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
		timer.scheduleAtFixedRate( new PacmanDoTask(m_form,pcID), (long)50* period, (long)50 * period /** 60 * 60 * period*/); 
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
		//System.out.println("PC ID "+ m_pcID);
		//System.out.println("time start:"+ );
	//	System.out.println("time start:"+ path.GetTime0()+ " ," + "get timeT:"+ path.GetTimeT() +", " + path.GetDeltatime());

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
	//ArrayList<Point>pnts = new ArrayList<>();
    public  enum GameObjectType {PackmanType,FruitType,Undefined};
    GameObjectType gameType2Add =GameObjectType.PackmanType;
	Image packmanIcon;
	BufferedImage packman;
	private JTextField field;
	private int idxP=0;
	private int idxF=0;

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

	
	private void initGUI() 
	{
	
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		Menu menu2= new Menu("File");
		MenuItem itemRun = new MenuItem("Game Run");
		MenuItem itemStop = new MenuItem("Game Stop");
		MenuItem itemPackman=new MenuItem("Add Packman");
		MenuItem itemFruit=new MenuItem("Add Fruit");
		MenuItem itemClear=new MenuItem("Clear Screen");
		MenuItem itemFile= new MenuItem("Load File");
		MenuItem itemSave= new MenuItem("Save Game");
		
		
		ActionListener argFile = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(e.getActionCommand()=="Load File")
				{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION || true)
			
				{
					String csvFileName = chooser.getSelectedFile().getPath();
					//String csvFileName="game_1543693911932_save1.csv";//
					//String csvFileName ="game_1543693911932.csv";
					game.LoadCsv(csvFileName);
				    System.out.println("You chose to open file: " + chooser.getSelectedFile().getName());
                    repaint();
                    pathManager= new ShortestPathAlgo(game);
                    pathManager.InitShortestPathAlgo();
                   
                    
    				if(returnVal == JFileChooser.CANCEL_OPTION)
    				{
    					field.setText("cancel");
    				}
    				       
				}
				
			}
			else 
			if(e.getActionCommand()=="Save Game")
			{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION || true)
			
				{
					String csvFileName = chooser.getSelectedFile().getPath();
			
				//String csvFileName="game_1543693911932_save1.csv";
				game.UpLoadCsv(csvFileName);
			}
				
				System.out.println("You chose to save file: " + chooser.getSelectedFile().getName());
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
		
		ActionListener argRun = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("menue a Clicked");
				x = 500;
				y = 500;
				if(pathManager == null)
					pathManager= new ShortestPathAlgo(game);
				
				pacsTimer.Start(1, 2);
				
				

			}
		};
		ActionListener argStop = new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//System.out.println("menue b Clicked");
				//timer.purge();
				pacsTimer.Stop();
			}
		};
		itemRun.addActionListener(argRun);
		itemStop.addActionListener(argStop);
//		itemClear.addActionListener(argClear);
		itemPackman.addActionListener(userSelectionListener);
		itemFruit.addActionListener(userSelectionListener);
		itemSave.addActionListener(argFile);
		itemFile.addActionListener(argFile);
		itemClear.addActionListener(userSelectionListener);
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
	public BufferedImage packmanImage;
	
	public void DrawPackmans(Graphics g, ArrayList<Packman> packmanLst) 
	{		
		try { 
			packmanImage = ImageIO.read(new File("ghost0.png")); 
		} 
		catch (IOException e) 
		
		{ e.printStackTrace();
		
		}
		for (Iterator<Packman> iterator = packmanLst.iterator(); iterator.hasNext();) 
		{
			Packman p = iterator.next();
			Point pixelPoint =MyMap.getPositionOnScreen(p.GetPoint3Dlocation().y(), p.GetPoint3Dlocation().x());
			g.drawImage(packmanImage,pixelPoint.x-15,pixelPoint.y-15,30,30,this);
		}
		
		
		
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
		//map.setSize(1378, 642);
		//g.drawImage(map.GetImage(),0, 0, this);
		g.drawImage(map.GetImage(), 0, 0, 1372,634, this);//1372,635
							
		ArrayList<Packman> packmanLst= game.GetArrListPac();
		if(packmanLst!=null) 
		DrawPackmans(g,packmanLst);
		ArrayList<Fruit> fruitLst= game.GetArrListFruit();	
		if(fruitLst!=null)
		DrawFruits(g,fruitLst);
		
	}
	
	ActionListener userSelectionListener=new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{ 
			//repaint()
			if(e.getActionCommand().equals("Clear Screen"))
			{
				game.ClearGame();
				repaint();
			}
			
			if(e.getActionCommand().equals("Add Packman"))
			{
				gameType2Add = GameObjectType.PackmanType;		
				
			}
			else if(e.getActionCommand().equals("Add Fruit"))
			{
				gameType2Add = GameObjectType.FruitType;
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
		//System.out.println("mouse Clicked");
		//System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		Point pnt= new Point(x, y);
		Point3D pnt3D= new Point3D(MyMap.getPositionOnMap(pnt));
		switch(gameType2Add)
		{
			case PackmanType:
			{
				game.AddPackman(new Packman(pnt3D,1,1,idxP));
				idxP++;
				break;
			}
			case FruitType:
			{
				game.AddFruit(new Fruit(idxF, 1, pnt3D));
				idxF++;
				break;
			}
			default:
			{
				break;
			}
		}
		
		repaint();
		
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
	//	System.out.println("mouse entered");

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
