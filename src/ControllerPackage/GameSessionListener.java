package ControllerPackage;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
import ModelPackage.*;
import ViewPackage.*;

public class GameSessionListener implements ActionListener, KeyListener
{
	//variables just to shorten code ( could type GameController.something...)
	private GameModel gameModel;
	private PlayerModel playerModel;
	private GameInterfaceView gameInterfaceView;
	private GameSceneView gameSceneView;

	//list of keys pressed now
	ArrayList<Integer> listOfPressedKeys;

	//variables used to spawn objects and repaint
	private Timer timer;
	private int time = 0; //cumulated time
	private int timeAsteroidSpawn = 0;
	private int timeBulletSpawn = 0;
	private int timeBonusSpawn = 0;
	private int timeSetTarget = 0;
	private int rechargeTime = 0; //time between player bullets shoots
	private int collisionTime = 0; //used just to reduce number of checks through lists ( example-> 10/sec instead 30/sec-> no visual difference)
	private int labelsUpdateTime = 0;

	boolean isAlreadyOnList = false; //if key is on list ( is already pressed)

	public GameSessionListener() 
	{
		setVariablesForUse();
	}

	//just to make code shorter ( could use GameController.something..., but it'd long)
	public void setVariablesForUse()
	{
		//assign variables
		gameModel = GameController.getGameModel();
		playerModel = GameController.getGameModel().getPlayerModel();
		gameSceneView = GameController.getGameView().getInterface().getScenePanel();
		gameInterfaceView = GameController.getGameView().getInterface();

		//initiate and assign variables
		gameSceneView.setPlayer(gameModel.getPlayer());
		listOfPressedKeys = new ArrayList<Integer>();
		timer = new Timer(30, this);
		gameModel.setTimerDelay(timer.getDelay()); //sets timer delay for use in LASER type bullets
	}

	public void setTimer(boolean turnON)
	{
		//turn on/off timer
		if (turnON == true) timer.start();
		else timer.stop();
	}

	//triggered on Timer ticks, series of methods to repaint scene with current status of a game
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//checks if continue game, or it's GAME OVER already
		checkLifeOfPlayer();

		//series of methods to update current situation on a scene ( position, spawn, collision)
		setPositionOfObjects();
		checkCollisionStatus();
		checkSpawnShip();
		checkSpawnAsteroid();
		checkSpawnBonus();
		checkSpawnBullet();
		checkSpawnPlayerBullet();
		CheckMissilesTarget();

		//update information in labels and clocks
		updateLabelsInGameInterface();
		updateClocks();
		
		//repaint a scene
		gameSceneView.repaint();
	}

	//check if life of player is below zero, if yes, stop game
	private void checkLifeOfPlayer()
	{
		if (gameModel.getPlayerModel().getPlayersLife(gameModel.getPlayer()) == 0)
		{
			setTimer(false);
			gameSceneView.setGameOverString("GAME OVER!");
			gameSceneView.repaint();
		}
	}

	//trigger setting new position of objects on lists
	public void setPositionOfObjects()
	{
		//sets new position of player, depending on pressed keys
		playerModel.calculateMovement(gameModel.getPlayer(), listOfPressedKeys);

		//sets new position of enemy ships and bullets
		gameModel.setNewPositionOfObjects();
		gameModel.setNewPositionOfBullets();
	}

	//checks collision
	public void checkCollisionStatus()
	{
		/*checks if there is collision between objects on lists;
		 * "if" just to reduce number of checks per second-> no need to check every timers tick*/
		if (collisionTime > 50)
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
		//spawns new enemy ship and resets time of spawn,
		if (time > (2000 - 75*gameModel.getLvlOfGame()))
		{
			gameModel.setNewEnemyShip();
			gameSceneView.updateListOfEnemyShips(gameModel.getListOfEnemyShips());
			time = 0;
		}
	}

	//check if can spawn Asteroid object
	public void checkSpawnAsteroid()
	{
		//spawns new asteroid
		if (timeAsteroidSpawn > 5000)
		{
			gameModel.setNewEnemyShipAsteroid();
			gameSceneView.updateListOfEnemyShips(gameModel.getListOfEnemyShips());
			timeAsteroidSpawn = 0;
		}
	}

	//checks if can spawn new Bonus object
	public void checkSpawnBonus()
	{
		//spawns new bonus
		if (timeBonusSpawn > 25000)
		{
			gameModel.setNewBonus();
			gameSceneView.updateListOfBonuses(gameModel.getListOfBonuses());
			timeBonusSpawn = 0;
		}
	}

	//check if can spawn new enemy bullets
	public void checkSpawnBullet()
	{
		//spawns new bullets of enemies obejcts, resets time between spawns
		if (timeBulletSpawn > 1500)
		{
			gameModel.setNewBullets();
			gameSceneView.updatelistOfEnemyBullets(gameModel.getListOfEnemyBullets());
			timeBulletSpawn = 0;
		}
	}

	//checks if can spawn Players bullets
	public void checkSpawnPlayerBullet()
	{
		//checks if we allow auto fire ( checkbox in game interface section)
		if (gameInterfaceView.getIfCheckboxChecked() == false)
		{
			//gets type of chosen weapon
			String typeOfWeapon = gameModel.getPlayerModel().getTypeOfWeapon(gameModel.getPlayer());

			//sets bullets depending on kind of chosen weapon
			if (rechargeTime > 500 && (typeOfWeapon == "MISSILES" || typeOfWeapon == "BLASTER")){
				gameModel.setNewBulletsOfPlayer();
				gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
				rechargeTime = 0;
			}
			else if (rechargeTime > 2000 && typeOfWeapon == "LASER"){
				gameModel.setNewBulletsOfPlayer();
				gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
				rechargeTime = 0;
			}
		}
	}

	//sets closest enemy ship as a target of a missiles on a scene, every 0.5sec
	public void CheckMissilesTarget()
	{
		if (timeSetTarget > 500) 
		{
			gameModel.setClosestEnemyToMissile();
			timeSetTarget = 0;
		}
	}

	//update texts in labels in game interface ( points, player hp etc)
	public void updateLabelsInGameInterface()
	{
		if (labelsUpdateTime > 200)
		{
			//update player stats
			gameInterfaceView.updateLabels("labelHP", "HP", playerModel.getPlayersLife(gameModel.getPlayer()));
			gameInterfaceView.updateLabels("labelShield", "Shield", playerModel.getPlayersShield(gameModel.getPlayer()));
			gameInterfaceView.updateLabels("labelPoints", "Points", playerModel.getPlayersPoints(gameModel.getPlayer()));
			gameInterfaceView.updateLabels("labelLevel", "Level", gameModel.getLvlOfGame());

			//update weapons stats
			updateLabelsWeaponInfo("labelMissileNumber", "Missile", "numberOfMissiles");
			updateLabelsWeaponInfo("labelMissilePower", "Missile power", "powerMissile");
			updateLabelsWeaponInfo("labelMissileSpeed", "Missile speed", "speedMissile");
			updateLabelsWeaponInfo("labelBlasterNumber", "Blaster", "numberOfBlaster");
			updateLabelsWeaponInfo("labelBlasterPower", "Blaster power", "powerBlaster");
			updateLabelsWeaponInfo("labelBlasterSpeed", "Blaster speed", "speedBlaster");
			updateLabelsWeaponInfo("labelLaserNumber", "Laser", "numberOfLaser");
			updateLabelsWeaponInfo("labelLaserPower", "Laser power", "powerLaser");
			updateLabelsWeaponInfo("labelBombNumber", "Bomb", "numberOfBombs");
			updateLabelsWeaponInfo("labelShieldMax", "Shield max", "maxShield");

			//reset time
			labelsUpdateTime = 0;
		}
	}

	//update pointed label in game interface, to shorten code
	private void updateLabelsWeaponInfo(String nameOfLabel, String textToDisplay, String valueToGet)
	{
		int value = playerModel.getInfo(gameModel.getPlayer(), valueToGet);
		gameInterfaceView.updateLabels(nameOfLabel, textToDisplay, value);
	}

	//adds timer tick time to clocks
	public void updateClocks()
	{
		time += timer.getDelay();
		timeAsteroidSpawn += timer.getDelay();
		timeBulletSpawn += timer.getDelay();
		timeBonusSpawn += timer.getDelay();
		timeSetTarget += timer.getDelay();
		rechargeTime += timer.getDelay();
		collisionTime += timer.getDelay();
		labelsUpdateTime += timer.getDelay();
	}

	//on key pressed event
	@Override
	public void keyPressed(KeyEvent e) 
	{
		//store code of pressed key
		int code = e.getKeyCode();

		//depending on pressed key perform one of the actions
		switch (code) 
		{
		case 32:
			//SPACE pressed, player shoots bullets (missile, blaster, laser, bomb)
			if (rechargeTime > 350 && gameModel.getPlayerModel().getTypeOfWeapon(gameModel.getPlayer()) != "LASER"){
				gameModel.setNewBulletsOfPlayer();
				gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
				rechargeTime = 0;
			}
			//shoot laser beam
			else if (rechargeTime > 2000 && gameModel.getPlayerModel().getTypeOfWeapon(gameModel.getPlayer()) == "LASER"){
				gameModel.setNewBulletsOfPlayer();
				gameSceneView.updateListOfPlayerBullets(gameModel.getListOfPlayerBullets());
				rechargeTime = 0;
			}
			break;
		case 37:
			//LEFT arrow pressed, move left
			addCodeToList(code);
			break;
		case 38:
			//UP arrow pressed, move up
			addCodeToList(code);
			break;
		case 39:
			//RIGHT arrow pressed, move right
			addCodeToList(code);
			break;
		case 40:
			//DOWN arrow pressed, move down
			addCodeToList(code);
			break;
		case 81:
			//Q pressed, weapon changed for missiles
			setTypeOfWeaponForPlayer("MISSILES");
			break;
		case 87:
			//W pressed, weapon changed for blaster
			setTypeOfWeaponForPlayer("BLASTER");
			break;
		case 69:
			//E pressed, weapon changed for laser
			setTypeOfWeaponForPlayer("LASER");
			break;
		case 82:
			//R pressed, weapon changed for bomb
			setTypeOfWeaponForPlayer("BOMB");
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
			//left arrow released
			removeCodeFromList(code);
			break;
		case 38:
			//up arrow released
			removeCodeFromList(code);
			break;
		case 39:
			//right arrow released
			removeCodeFromList(code);
			break;
		case 40:
			//down arrow released
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

	//sets type of weapon depending on clicked button with weapons
	public void setTypeOfWeaponForPlayer(String typeOfWeapon)
	{
		GameController.getGameModel().getPlayerModel().setTypeOfWeapon(GameController.getGameModel().getPlayer(), typeOfWeapon);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
