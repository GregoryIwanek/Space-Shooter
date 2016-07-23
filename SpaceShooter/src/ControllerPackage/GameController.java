package ControllerPackage;
import ModelPackage.*;
import ViewPackage.*;

public class GameController 
{
	static GameView gameView;
	static GameModel gameModel;
	
	static GameMenuListener gameMenuListener;
	static GameInterfaceListener gameInterfaceListener;
	
	public GameController(GameView gameView, GameModel gameModel) 
	{
		//setting view and model;
		GameController.gameView = gameView;
		GameController.gameModel = gameModel;
		
		//setting listeners
		gameMenuListener = new GameMenuListener(gameView, gameModel);
		GameController.gameView.addActionListenerMainMenu(gameMenuListener);
		
		gameInterfaceListener = new GameInterfaceListener(gameView, gameModel);
		GameController.gameView.addActionListenerGameInterface(gameInterfaceListener);
		GameController.gameView.addKeyListenerGame(gameInterfaceListener.getGameSessionListener());
		
		GameController.gameInterfaceListener.getGameSessionListener().setPlayerViewAndModel(gameView.getInterface().getScenePanel().getPlayerViewView(),
				gameModel.getPlayerModel());
		GameController.gameInterfaceListener.getGameSessionListener().setGameInterfaceView(gameView.getInterface());
	}
}
