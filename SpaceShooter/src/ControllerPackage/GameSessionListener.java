package ControllerPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import ModelPackage.GameModel;
import ModelPackage.PlayerModel;
import ViewPackage.GameInterfaceView;
import ViewPackage.GameSceneView;

public class GameSessionListener implements ActionListener, KeyListener
{
	//variables to shorten code ( could type GameController.something..., but it's long)
	private GameModel gameModel;
	private PlayerModel playerModel;
	private GameInterfaceView gameInterfaceView;
	private GameSceneView gameSceneView;

	ArrayList<Integer> listOfPressedKeys;

	//variables used to spawn objects and repaint
	private Timer timer;
	private int timeCumulated = 0;
	private int timeCumulatedBulletSpawn = 0;
	private int rechargeTime = 0; //time between player bullets shoots
	private int collisionTime = 0; //used just to reduce number of checks through lists ( example-> 10/sec instead 30/sec-> no visual difference)

	boolean isAlreadyOnList = false; //if key is on list ( is already pressed)

	public GameSessionListener() 
	{
		setVariablesForUse();
	}

	//just to make code shorter ( could use GameController.something..., but it's long)
	public void setVariablesForUse()
	{
		gameModel = GameController.getGameModel();
		playerModel = GameController.getGameModel().getPlayerModel();
		gameSceneView = GameController.getGameView().getInterface().getScenePanel();
		gameInterfaceView = GameController.getGameView().getInterface();
		
		//initiate and assign variables
		gameSceneView.setPlayer(gameModel.getPlayer());
		listOfPressedKeys = new ArrayList<Integer>();
		timer = new Timer(30, this);
	}

	public void setTimer(boolean turnON)
	{
		if (turnON == true) timer.start();
		else timer.stop();
	}

	//triggered on Timer ticks, series of methods to repaint scene with current status of a game
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//series of methods to update current situation on a scene
		setPositionOfObjects();
		checkCollisionStatus();
		checkSpawnShip();
		checkSpawnBullet();

		//repaint scene
		gameSceneView.repaint();

		//update information in labels and clocks
		updateLabelsInGameInterface();
		updateClocks();
	}

	//trigger setting new position of objects on lists
	public void setPositionOfObjects()
	{
		//sets new position of player, depending on pressed keys
		playerModel.calculateMovementOfPlayer(gameModel.getPlayer(), listOfPressedKeys);

		//sets new position of enemy ships and bullets
		gameModel.setNewPositionOfShips();
		gameModel.setNewPositionOfBullets();
	}
	
	//checks collision
	public void checkCollisionStatus()
	{
		/*checks if there is collision between objects on lists;
		 * "if" just to reduce number of checks per second-> no need to check every timer tick*/
		if (collisionTime > 100)
		{
			gameModel.checkIfEnemyInCollision(gameModel.getPlayer());
			gameModel.checkIfBulletInCollision(gameModel.getPlayer());
			gameModel.checkIfPlayerBulletInCollision();
			collisionTime = 0;
		}
	}

	//check if can spawn new enemy ship
	public void checkSpawnShip()
	{
		//spawns new enemy ship and resets time of spawn
		if (timeCumulated > 1500)
		{
			gameModel.setNewEnemyShip();
			gameSceneView.updateListOfEnemyShips(gameModel.getListOfEnemyShips());
			timeCumulated = 0;
		}
	}

	//check if can spawn new enemy bullets
	public void checkSpawnBullet()
	{
		//spawns new bullets of enemies, resets time between spawns
		if (timeCumulatedBulletSpawn > 800)
		{
			gameModel.setNewBullets();
			gameSceneView.updateListOfBullets(gameModel.getListOfEnemyBullets());
			timeCumulatedBulletSpawn = 0;
		}
	}

	//update texts in labels in game interface ( points, player hp etc)
	public void updateLabelsInGameInterface()
	{
		gameInterfaceView.updateLabels("labelHP", playerModel.getPlayersLife(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelShield", playerModel.getPlayersShield(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelPoints", playerModel.getPlayersPoints(gameModel.getPlayer()));
		gameInterfaceView.updateLabels("labelLevel", playerModel.getPlayersPoints(gameModel.getPlayer())/50+1);
	}
	
	//adds timer tick time to clocks
	public void updateClocks()
	{
		timeCumulated += timer.getDelay();
		timeCumulatedBulletSpawn += timer.getDelay();
		rechargeTime += timer.getDelay();
		collisionTime += timer.getDelay();
	}

	//on key pressed event
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
			//left button pressed, move left
			addCodeToList(code);
			break;
		case 38:
			//up button pressed, move up
			addCodeToList(code);
			break;
		case 39:
			//right button pressed, move right
			addCodeToList(code);
			break;
		case 40:
			//down button pressed, move down
			addCodeToList(code);
			break;
		default:
			break;
		}
	}

	//on key released events
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

	//removes released keys from list
	public void removeCodeFromList(int code)
	{
		listOfPressedKeys.remove(Integer.valueOf(code));
		isAlreadyOnList = false;
	}

	//adds pressed keys to list
	public void addCodeToList(int code)
	{
		//checks if key which is pressed is already on list
		if (listOfPressedKeys.size()> 0)
		{
			for (int element : listOfPressedKeys)
			{
				//if pressed key is on list, set that it's on list
				if (element == code) isAlreadyOnList = true;
			}
		}

		//adds to list, if key isn't already on list
		if (isAlreadyOnList == false) listOfPressedKeys.add(code);
		else isAlreadyOnList = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
