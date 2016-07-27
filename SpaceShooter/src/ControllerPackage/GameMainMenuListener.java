package ControllerPackage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ModelPackage.GameModel;
import ViewPackage.GameView;

public class GameMainMenuListener implements ActionListener 
{
	GameView gameView;
	GameModel gameModel;
	
	public GameMainMenuListener(GameView gameView, GameModel gameModel) 
	{
		//setting view and model;
		this.gameView = gameView;
		this.gameModel = gameModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//starts game or exits program
		if (((Component) e.getSource()).getName() == "START GAME")
		{
			this.gameView.startEndSession();
		}
		else if (((Component) e.getSource()).getName() == "QUIT")
		{
			System.exit(0);
		}
	}
}
