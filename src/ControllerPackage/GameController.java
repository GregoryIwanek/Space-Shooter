package ControllerPackage;
import ModelPackage.*;
import ViewPackage.*;

public class GameController 
{
	//main game view and main game model
	static private GameView gameView;
	static private GameModel gameModel;
	
	static public MainMenuListener mainMenuListener; //listener which gets clicks events from main menu
	static public InterfaceListener interfaceListener; //listener which gets clicks events from game interface
	static public SessionListener sessionListener; //key listener which contains Timer and is responsible for a game session
	
	public GameController(GameView gameView, GameModel gameModel) 
	{
		//assign given view and model
		setViewAndModel(gameView, gameModel);
		
		//assign action and key listeners
		setListeners();
	}
	
	public void setViewAndModel(GameView gameView, GameModel gameModel)
	{
		//assigning main view and main model;
		GameController.gameView = gameView;
		GameController.gameModel = gameModel;
	}
	
	//setting and assigning all listeners
	public void setListeners()
	{
		//setting and assigning main menu listener
		mainMenuListener = new MainMenuListener();
		GameController.gameView.addActionListenerMainMenu(mainMenuListener);
		
		//setting and assigning game interface listener
		interfaceListener = new InterfaceListener();
		GameController.gameView.addActionListenerGameInterface(interfaceListener);
		
		//setting and assigning game session listener
		sessionListener = new SessionListener();
		GameController.gameView.addKeyListenerGame(sessionListener);
	}
	
	static public GameView getGameView()
	{
		return gameView;
	}
	static public GameModel getGameModel()
	{
		return gameModel;
	}
}
