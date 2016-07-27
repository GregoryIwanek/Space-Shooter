package ViewPackage;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameView extends JFrame
{
	private static MainMenuView mainMenuView;
	private static GameInterfaceView gameInterfaceView;
	private static Dimension dimension;

	public GameView() 
	{
		this.setFocusable(true);
		
		//definition of basic information about starting position of window
		dimension = Toolkit.getDefaultToolkit().getScreenSize();

		//definition of main frame ( window with scene to paint on), fixed size
		this.setName("Space Shooter");
		this.setSize(800,800);
		this.setResizable(false);
		
		//setting starting location of window on the screen
		this.setLocation(dimension.width/2 - this.getWidth()/2,
				dimension.height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//definition of main menu panel
		mainMenuView = new MainMenuView();
		mainMenuView.setGeometryOfButtons(this.getWidth(), this.getHeight());
		this.add(mainMenuView, BorderLayout.EAST);
		mainMenuView.setVisible(true);
		
		gameInterfaceView = new GameInterfaceView();
		gameInterfaceView.setVisible(false);
	}
	
	//sets action listener for game session
	public void addActionListenerGameInterface (ActionListener actionListenerMainMenu)
	{
		gameInterfaceView.addActionListenerGameInterface(actionListenerMainMenu);
	}
	
	//set action listener for main menu
	public void addActionListenerMainMenu(ActionListener actionListenerMainMenu)
	{
		mainMenuView.addActionListenerMainMenu(actionListenerMainMenu);
	}
	
	public void addKeyListenerGame (KeyListener keyListenerGame)
	{
		this.addKeyListener(keyListenerGame);
	}

	//switch between game mode and main menu mode
	public void startEndSession()
	{
		if (gameInterfaceView.isVisible() == false)
		{
			this.add(gameInterfaceView);
			mainMenuView.setVisible(false);
			gameInterfaceView.setVisible(true);
		}
		else 
		{
			gameInterfaceView.setVisible(false);
			this.remove(gameInterfaceView);
			mainMenuView.setVisible(true);
		}
	}
	
	public GameInterfaceView getInterface()
	{
		return gameInterfaceView;
	}
}
