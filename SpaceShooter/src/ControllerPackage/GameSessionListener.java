package ControllerPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import ModelPackage.Bullet;
import ModelPackage.GameModel;
import ModelPackage.PlayerModel;
import ModelPackage.PlayerView;
import ViewPackage.GameInterfaceView;
import ViewPackage.GameSceneView;

public class GameSessionListener implements ActionListener, KeyListener
{
	PlayerView playerView;
	PlayerModel playerModel;
	GameSceneView gameSceneView;
	GameModel gameModel;
	GameInterfaceView gameInterfaceView;
	static ArrayList<Integer> listOfPressedKeys;
	static ArrayList<Bullet> listOfBullets;
	private Timer timer;
	private int timeCumulated = 0;
	
	boolean isAlreadyOnList = false;
	
	public GameSessionListener(GameSceneView gameSceneView, GameModel gameModel) 
	{
		this.gameSceneView = gameSceneView;
		listOfPressedKeys = new ArrayList<Integer>();
		listOfBullets = new ArrayList<Bullet>();
		timer = new Timer(30, this);
		this.gameModel = gameModel;
	}
	
	public void setPlayerViewAndModel(PlayerView playerView, PlayerModel playerModel)
	{
		this.playerView = playerView;
		this.playerModel = playerModel;
		
		setCommonData(this.playerView, this.playerModel);
	}
	public void setCommonData(PlayerView playerView, PlayerModel playerModel)
	{
		playerModel.setSize(playerView.getBounds().width, playerView.getBounds().height);

	}
	public void setGameInterfaceView(GameInterfaceView gameInterfaceView)
	{
		this.gameInterfaceView = gameInterfaceView;
	}
	
	public void setTimer(boolean turnON)
	{
		if (turnON == true) timer.start();
		else timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		playerModel.calculateMovementOfPlayer(listOfPressedKeys);
		gameSceneView.updatePlayerViewPosition(playerModel.getNewPosition());
		
		gameModel.setNewPositionOfShips();
		gameModel.setNewPositionOfBullets();
		
		gameModel.checkIfEnemyInCollision(playerView);
		gameModel.checkIfBulletInCollision(playerView);
		gameModel.checkIfPlayerBulletInCollision();
		
		if (timeCumulated > 1500)
		{
			gameModel.setNewEnemyShip();
			gameSceneView.updateListOfEnemyShips(gameModel.getListOfEnemyShips());
			gameModel.setNewBullets();
			gameSceneView.updateListOfBullets(gameModel.getListOfBullets());
			timeCumulated = 0;
		}
		
		gameSceneView.repaint();
		
		gameInterfaceView.updateLabels("labelHP", playerModel.getLife());
		gameInterfaceView.updateLabels("labelShield", playerModel.getShield());
		
		timeCumulated += timer.getDelay();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) addCodeToList(code);
		else if (code == KeyEvent.VK_RIGHT) addCodeToList(code);
		else if(code == KeyEvent.VK_LEFT) addCodeToList(code);
		else if (code == KeyEvent.VK_DOWN) addCodeToList(code);
		
		if (code == KeyEvent.VK_SPACE)
		{
			gameModel.setNewBulletsOfPlayer();
			gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) removeCodeFromList(code);
		else if (code == KeyEvent.VK_RIGHT) removeCodeFromList(code);
		else if(code == KeyEvent.VK_LEFT) removeCodeFromList(code);
		else if (code == KeyEvent.VK_DOWN) removeCodeFromList(code);	
	}
	
	public void removeCodeFromList(int code)
	{
		listOfPressedKeys.remove(Integer.valueOf(code));
		isAlreadyOnList = false;
	}
	public void addCodeToList(int code)
	{
		if (listOfPressedKeys.size()> 0)
		{
			for(int element : listOfPressedKeys)
			{
				if (element == code) isAlreadyOnList = true;
			}
		}
		if (isAlreadyOnList == false) listOfPressedKeys.add(code);
		else isAlreadyOnList = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
