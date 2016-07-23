package ViewPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuView extends JPanel
{
	//panel with picture
	private JLabel picturePanel;
	//panel with buttons
	private JPanel mainMenuPanel;

	//buttons
	private JButton startButton;
	private JButton quitButton;
	
	private BufferedImage imageOfMenu;
	private BufferedImage imageOfRest;

	//default constructor
	public MainMenuView()
	{	
		//initiation of panel with picture
		picturePanel = new JLabel();
		picturePanel.setBackground(Color.green);
		picturePanel.setPreferredSize(new Dimension(530, 760));
		
		//initiation of main menu buttons and container (panel)
		mainMenuPanel = new JPanel();
		startButton = new JButton("START GAME");
		startButton.setName("START GAME");
		quitButton = new JButton("QUIT");
		quitButton.setName("QUIT");
		startButton.setBackground(Color.CYAN);
		quitButton.setBackground(Color.CYAN);

		//definition of this panel
		mainMenuPanel.setBackground(Color.blue);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 50));
		mainMenuPanel.setPreferredSize(new Dimension(250,760));
		
		//adding components to the panel
		mainMenuPanel.add(startButton);
		mainMenuPanel.add(quitButton);	

		add(picturePanel);
		add(mainMenuPanel);
		
		setImagesOfMenu(imageOfRest, "/welcome_image.jpg");
	}
	
	//setting size of buttons
	public void setGeometryOfButtons(int widthFrame, int heightFrame)
	{
		//set size of buttons
		startButton.setPreferredSize(new Dimension(widthFrame/5,heightFrame/9));
		quitButton.setPreferredSize(new Dimension(widthFrame/5,heightFrame/9));
	}
	
	//adding listeners for click event on buttons
	public void addActionListenerMainMenu(ActionListener actionListener)
	{
		startButton.addActionListener(actionListener);
		quitButton.addActionListener(actionListener);
	}
	
	//setting images to panels
	public void setImagesOfMenu(BufferedImage image, String path)
	{
		try {
			imageOfRest = ImageIO.read(getClass().getResourceAsStream("/welcome_image.jpg"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imageOfRest, picturePanel.getX(), picturePanel.getY(),
				picturePanel.getSize().width, picturePanel.getSize().height, null);
	}
}
