package ViewPackage;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameView extends JFrame
{
	private static MainMenuView mainMenuView; //view of main menu
	private static GameInterfaceView gameInterfaceView; //view of game interface and sub-panels
	private static Dimension dimension;

	public GameView() 
	{
		//to get keys events and clicks from user
		this.setFocusable(true);

		//to define basic information about starting position of window on screen
		dimension = Toolkit.getDefaultToolkit().getScreenSize();

		//setting main menu and game interface
		setMainFrame();
		setMainMenu();
		setGameInterface();
	}

	//set main window of program
	public void setMainFrame()
	{
		//definition of main frame of program, fixed size
		this.setName("Space Shooter");
		this.setSize(800,800);
		this.setResizable(false);

		//setting starting location of window on the screen
		this.setLocation(dimension.width/2 - this.getWidth()/2, dimension.height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//set main menu of program
	public void setMainMenu()
	{
		//definition of main menu panel, geometry
		mainMenuView = new MainMenuView();
		mainMenuView.setGeometryOfButtons(this.getWidth(), this.getHeight());
		this.add(mainMenuView, BorderLayout.EAST);
		mainMenuView.setVisible(true);
	}

	//sets game interface panel ( and sub-panels with buttons and labels)
	public void setGameInterface()
	{
		gameInterfaceView = new GameInterfaceView();
		gameInterfaceView.setVisible(false);
	}

	//sets action listener for game interface
	public void addActionListenerGameInterface (ActionListener actionListenerMainMenu)
	{
		gameInterfaceView.addActionListenerGameInterface(actionListenerMainMenu);
	}

	//set action listener for main menu
	public void addActionListenerMainMenu(ActionListener actionListenerMainMenu)
	{
		mainMenuView.addActionListenerMainMenu(actionListenerMainMenu);
	}

	//sets key listener to get key events from keyboard
	public void addKeyListenerGame (KeyListener keyListenerGame)
	{
		this.addKeyListener(keyListenerGame);
	}

	//switch between game session and main menu
	public void startEndGameSession()
	{
		//if game section invisible, set it visible and hide main menu
		if (gameInterfaceView.isVisible() == false)
		{
			this.add(gameInterfaceView);
			mainMenuView.setVisible(false);
			gameInterfaceView.setVisible(true);
		}
		//if game section visible, set it invisible, show main menu ( timer is stopped)
		else 
		{
			gameInterfaceView.setVisible(false);
			this.remove(gameInterfaceView);
			mainMenuView.setVisible(true);
		}
	}

	//gets game interface
	public GameInterfaceView getInterface()
	{
		return gameInterfaceView;
	}
}
