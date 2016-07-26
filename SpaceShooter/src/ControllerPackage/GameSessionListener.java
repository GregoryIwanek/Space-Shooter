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
import ModelPackage.Player;
import ViewPackage.GameInterfaceView;
import ViewPackage.GameSceneView;

public class GameSessionListener implements ActionListener, KeyListener
{
	PlayerModel playerModel;
	GameSceneView gameSceneView;
	GameModel gameModel;
	GameInterfaceView gameInterfaceView;
	static ArrayList<Integer> listOfPressedKeys;
	static ArrayList<Bullet> listOfBullets;
	private Timer timer;
	private int timeCumulated = 0;
	private int timeCumulatedBulletSpawn = 0;
	int rechargeTime = 0;
	int collisionTime = 0;
	
	boolean isAlreadyOnList = false;
	
	public GameSessionListener(GameSceneView gameSceneView, GameModel gameModel) 
	{
		this.gameSceneView = gameSceneView;
		listOfPressedKeys = new ArrayList<Integer>();
		listOfBullets = new ArrayList<Bullet>();
		timer = new Timer(30, this);
		this.gameModel = gameModel;
	}
	
	public void setPlayerViewAndModel(Player playerView, PlayerModel playerModel)
	{
		this.playerModel = playerModel;
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
		playerModel.calculateMovementOfPlayer(gameModel.getPlayer(), listOfPressedKeys);
		gameSceneView.updatePlayerViewPosition(playerModel.getNewPosition(gameModel.getPlayer()));
		
		gameModel.setNewPositionOfShips();
		gameModel.setNewPositionOfBullets();
		
			gameModel.checkIfEnemyInCollision(gameModel.getPlayer());
			gameModel.checkIfBulletInCollision(gameModel.getPlayer());
			gameModel.checkIfPlayerBulletInCollision();
	
		if (timeCumulated > 1500)
		{
			gameModel.setNewEnemyShip();
			gameSceneView.updateListOfEnemyShips(gameModel.getListOfEnemyShips());
			timeCumulated = 0;
		}
		
		if (timeCumulatedBulletSpawn > 800)
		{
			gameModel.setNewBullets();
			//gameModel.setNewBlasterBullets(2, 500, 0.5, 1);
			gameSceneView.updateListOfBullets(gameModel.getListOfBullets());
			timeCumulatedBulletSpawn = 0;
		}
		gameSceneView.repaint();
		
		updateLabelsInGameInterface();
		
		timeCumulated += timer.getDelay();
		timeCumulatedBulletSpawn += timer.getDelay();
		rechargeTime += timer.getDelay();
	}
	
	//update texts in labels in interface ( points, players hp etc)
	public void updateLabelsInGameInterface()
	{
		gameInterfaceView.updateLabels("labelHP", playerModel.getLife(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelShield", playerModel.getShield(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelPoints", playerModel.getPoints(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelLevel", playerModel.getPoints(gameModel.getPlayer())/50+1);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		//store code of pressed key
		int code = e.getKeyCode();

		//depending on pressed key perform one of the actions
		switch (code) {
		case 32:
			//space pressed, player shoots bullets
			if (rechargeTime > 250){
				gameModel.setNewBulletsOfPlayer();
				gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
				rechargeTime = 0;
			}
			break;
		case 37:
			//left button pressed
			addCodeToList(code);
			break;
		case 38:
			//up button pressed
			addCodeToList(code);
			break;
		case 39:
			//right button pressed
			addCodeToList(code);
			break;
		case 40:
			//down button pressed
			addCodeToList(code);
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		//get code of released key
		int code = e.getKeyCode();
		
		//depending on released key perform action
		switch (code) {
		case 37:
			//left button released
			removeCodeFromList(code);
			break;
		case 38:
			//up button released
			removeCodeFromList(code);
			break;
		case 39:
			//right button released
			removeCodeFromList(code);
			break;
		case 40:
			//down button released
			removeCodeFromList(code);
			break;
		default:
			break;
		}
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
			for (int element : listOfPressedKeys)
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
