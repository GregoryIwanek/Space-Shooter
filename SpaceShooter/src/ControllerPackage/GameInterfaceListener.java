package ControllerPackage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import ModelPackage.GameModel;
import ViewPackage.GameView;

public class GameInterfaceListener implements ActionListener
{
	GameView gameView;
	GameModel gameModel;
	GameSessionListener gameSessionListener;

	public GameInterfaceListener(GameView gameView, GameModel gameModel) 
	{
		//setting view and model;
		this.gameView = gameView;
		this.gameModel = gameModel;
		//setting list of pressed keys

		//setting game session listener
		gameSessionListener = new GameSessionListener(gameView.getInterface().getScenePanel(), gameModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (((Component) e.getSource()).getName() == "START")
		{
			gameSessionListener.setTimer(true);
		}
		else if (((Component) e.getSource()).getName() == "BACK")
		{
			gameSessionListener.setTimer(false);
			gameView.startEndSession();
		}
	}

	public GameSessionListener getGameSessionListener()
	{
		return gameSessionListener;
	}
}
