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

	//main menu buttons
	private JButton startButton;
	private JButton quitButton;
	
	//image in main menu
	private BufferedImage imageOfMenu;

	//default constructor
	public MainMenuView()
	{	
		//methods to define sub-panels of main menu section
		setImagesOfMenu(imageOfMenu, "/welcome_image.jpg");
		setPanelWithPicture();
		setMainMenuPanel();
		setButtons();
		addButtons();

		//adds sub-panels to main panel
		add(picturePanel);
		add(mainMenuPanel);
	}
	
	public void setPanelWithPicture()
	{
		//initiation of panel with a picture
		picturePanel = new JLabel();
		picturePanel.setPreferredSize(new Dimension(530, 760));
	}
	
	//sets panel with a buttons
	public void setMainMenuPanel()
	{
		//initiation of main menu buttons container
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(Color.blue);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 50));
		mainMenuPanel.setPreferredSize(new Dimension(250,760));
	}
	
	//sets buttons in main menu panel
	public void setButtons()
	{
		startButton = new JButton("START GAME");
		startButton.setName("START GAME");
		quitButton = new JButton("QUIT");
		quitButton.setName("QUIT");
		startButton.setBackground(Color.CYAN);
		quitButton.setBackground(Color.CYAN);
	}
	
	//puts buttons in main menu panel
	public void addButtons()
	{
		//adding components to the panel
		mainMenuPanel.add(startButton);
		mainMenuPanel.add(quitButton);	
	}
	
	//setting size of buttons, called from parent class
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
			//sets image to given picture path
			imageOfMenu = ImageIO.read(getClass().getResourceAsStream("/welcome_image.jpg"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//paints image in picture panel
	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imageOfMenu, picturePanel.getX(), picturePanel.getY(),
				picturePanel.getSize().width, picturePanel.getSize().height, null);
	}
}
