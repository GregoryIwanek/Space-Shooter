package ControllerPackage;
import ModelPackage.*;
import ViewPackage.*;

public class GameController 
{
	static GameView gameView;
	static GameModel gameModel;
	
	static GameMainMenuListener gameMainMenuListener;
	static GameInterfaceListener gameInterfaceListener;
	
	public GameController(GameView gameView, GameModel gameModel) 
	{
		setViewAndModel(gameView, gameModel);
		
		//setting listeners
		gameMainMenuListener = new GameMainMenuListener(gameView, gameModel);
		GameController.gameView.addActionListenerMainMenu(gameMainMenuListener);
		
		gameInterfaceListener = new GameInterfaceListener(gameView, gameModel);
		GameController.gameView.addActionListenerGameInterface(gameInterfaceListener);
		GameController.gameView.addKeyListenerGame(gameInterfaceListener.getGameSessionListener());
		
		GameController.gameInterfaceListener.getGameSessionListener().setPlayerViewAndModel(gameView.getInterface().getScenePanel().getPlayerView(),
				gameModel.getPlayerModel());
		GameController.gameInterfaceListener.getGameSessionListener().setGameInterfaceView(gameView.getInterface());
	}
	
	public void setViewAndModel(GameView gameView, GameModel gameModel)
	{
		//setting main view and main model;
		GameController.gameView = gameView;
		GameController.gameModel = gameModel;
	}
	public void setListeners()
	{
		
	}
}
