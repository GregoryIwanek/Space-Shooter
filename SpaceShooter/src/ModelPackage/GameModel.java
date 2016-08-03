package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ListIterator;

public class GameModel 
{
	static private PlayerModel playerModel;
	static private EnemyModel enemyModel;
	static private BulletsModel bulletsModel;
	static private BonusModel bonusModel;

	static private Player player;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfEnemyBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;
	static private ArrayList<Bonus> listOfBonuses;

	static private int bonusCount = 0; //counts number of dropped bonuses
	static private int lvlOfGame = 1;
	static private int timerDelay; //(FOR LASER BULLET ONLY) to determine lifetime of laser bullet and when to remove it
	static private boolean ifContainsMissiles = false; //true if there are missiles on a scene
	static private boolean ifLaserInCollision = false; //true if laser bullet is in collision with target

	public GameModel()
	{
		setObjects();
		setPlayer();
	}

	public void setObjects()
	{
		//initiate player object ( no need list for it, there is just one)
		player = new Player();

		//initiate sub-models
		playerModel = new PlayerModel();
		enemyModel = new EnemyModel();
		bulletsModel = new BulletsModel();
		bonusModel = new BonusModel();

		//initiate lists
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfEnemyBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
		listOfBonuses = new ArrayList<Bonus>();
	}

	public void setTimerDelay(int timerDelay)
	{
		this.timerDelay = timerDelay;
	}

	//sets type of chosen by player weapon (missiles, blaster, bomb etc)
	public void setTypeOfPlayerWeapon (String typeOfWeapon)
	{
		playerModel.setTypeOfWeapon(player, typeOfWeapon);
	}

	//(ONLY FOR MISSILES) sets true if player shoots missiles and they are present on a scene
	public void setIfContainsMissiles(boolean ifContainsMissiles)
	{
		GameModel.ifContainsMissiles = ifContainsMissiles;
	}

	/*(ONLY FOR MISSILES) checks if given bullet is a MISSILE, update status if yes;
	 * it's used to determine if they are MISSILES on a scene or not*/
	public void checkIfMissilesOnScene(Bullet bullet)
	{
		if (bulletsModel.getType(bullet) == "MISSILE")
		{
			setIfContainsMissiles(true);
		}
	}


	//assign closest enemy object to a missile
	public void setClosestEnemyToMissile()
	{
		//trigger only if missiles are on a list and they are enemies on a scene
		if (ifContainsMissiles == true && listOfEnemyShips.size()>0)
		{
			//reset information about missiles on scene, will determine if they are there inside loop
			setIfContainsMissiles(false);

			for (Bullet bullet : listOfPlayerBullets)
			{
				//sets closest enemy to a MISSILE bullet
				defineClosestEnemy(bullet);

				/*update information about missiles on scene here;
				 * if one of bullets on a list is a MISSILE, then loop will be activated on next timer tick;
				 * otherwise, it won't be triggered */
				checkIfMissilesOnScene(bullet);
			}
		}
	}

	public void defineClosestEnemy(Bullet bullet)
	{
		if (bullet.getType() == "MISSILE")
		{
			double longestDistance = 2000;
			for (Enemy enemy :listOfEnemyShips)
			{
				if (enemyModel.getIfEnemyAsteroid(enemy) == false)
				{
					double distanceToCheck = Math.sqrt(Math.pow(bullet.getCenterX() - enemy.getCenterX(), 2) +
							Math.pow(bullet.getCenterY() - enemy.getCenterY(), 2));

					if (longestDistance > distanceToCheck)
					{
						longestDistance = distanceToCheck;
						bulletsModel.setTargetForMissile(bullet, enemy);
					}
				}
			}
		}
	}

	//sets player object information
	public void setPlayer()
	{
		playerModel.setImage(player, "/Player.png");
		playerModel.setSize(player, new Dimension(45,65));
	}

	//sets new enemy ship, define information and adds to list of objects to paint
	public void setNewEnemyShip()
	{
		Enemy newEnemy = new Enemy();

		//sets enemy object data
		enemyModel.setEnemySize(newEnemy, new Dimension(35,50));
		enemyModel.setImage(newEnemy, "/Enemy_ship_1.png");
		enemyModel.setNewPosition(newEnemy, enemyModel.getRandomStartingPos(), 0);
		enemyModel.setEnemyLife(newEnemy, 800+lvlOfGame*300);
		enemyModel.setEnemySpeed(newEnemy, 1, false);

		//adds to painting list 
		listOfEnemyShips.add(newEnemy);
	}

	//sets new asteroid object ( fast moving enemy object)
	public void setNewEnemyShipAsteroid()
	{
		Enemy newEnemy = new Enemy();

		//sets enemy object data
		enemyModel.setEnemySize(newEnemy, new Dimension(35,50));
		enemyModel.setImage(newEnemy, "/rock.png");
		enemyModel.setNewPosition(newEnemy, playerModel.getNewPosition(player).x, 0);
		enemyModel.setEnemyIfAsteroid(newEnemy, true);
		enemyModel.setEnemyLife(newEnemy, 5000+250*lvlOfGame);
		enemyModel.setEnemySpeed(newEnemy, 8, true);

		//adds to painting list 
		listOfEnemyShips.add(newEnemy);
	}

	//sets new bonus object
	public void setNewBonus()
	{
		Bonus bonus = new Bonus();

		//sets object data
		bonusModel.setBonusSize(bonus, new Dimension(35,50));
		bonusModel.rollNewType(bonus, player, bonusCount);
		bonusModel.setNewPosition(bonus, bonusModel.getRandomStartingPos(), 0);
		bonusModel.setImage(bonus);

		//adds to painting list
		listOfBonuses.add(bonus);
		++bonusCount;
	}

	//sets position of an enemy ships ( triggered by Timer tick from controller)
	public void setNewPositionOfShips()
	{
		//update positions and checks if out of scene
		updatePositionOfShips();
		checkIfEnemyOutOfScene();
	}

	//update position of enemy ships after Timers tick
	public void updatePositionOfShips()
	{	
		//update position of every enemy ships on a list 
		for (Enemy enemy : listOfEnemyShips)
		{
			if (enemyModel.getPosition(enemy).y < 700) //650 is a bottom of a panel
			{
				enemyModel.setNewPosition(enemy, enemyModel.getPosition(enemy).x,
						enemyModel.getPosition(enemy).y+enemyModel.getSpeed(enemy));
			}
		}

		//update position of every bonus on a list
		for (Bonus bonus : listOfBonuses)
		{
			if (bonusModel.getPosition(bonus).y < 700)
			{
				bonusModel.setNewPosition(bonus, bonusModel.getPosition(bonus).x, bonusModel.getPosition(bonus).y+1);
			}
		}
	}

	//check if enemy ship is out of scene and should be removed
	public void checkIfEnemyOutOfScene()
	{
		ListIterator<Enemy> listOfEnemyShipsIterator = listOfEnemyShips.listIterator();
		while (listOfEnemyShipsIterator.hasNext())
		{ 
			Enemy enemyToCheck = listOfEnemyShipsIterator.next();
			if (enemyModel.getPosition(enemyToCheck).y > 640) //650 bottom of panel
			{
				listOfEnemyShipsIterator.remove();
			}
		}

		ListIterator<Bonus> listOfBonusesIterator = listOfBonuses.listIterator();
		while (listOfBonusesIterator.hasNext())
		{
			Bonus bonusToCheck = listOfBonusesIterator.next();
			if (bonusModel.getPosition(bonusToCheck).y > 640)
			{
				listOfBonusesIterator.remove();
			}
		}
	}

	//checks if enemy ship is in collision with a player object on a scene
	public void checkIfEnemyInCollision(Player player)
	{
		ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
		while (enemyIterator.hasNext())
		{
			Rectangle enemyRectangle = enemyIterator.next().getBounds();

			//only if player and checked enemy are on each other
			if (player.intersects(enemyRectangle))
			{	
				//remove enemy
				enemyIterator.remove();

				//update player stats
				playerModel.setShieldAndLife(player, -25, 0);
			}
		}

		ListIterator<Bonus> bonusIterator = listOfBonuses.listIterator();
		while (bonusIterator.hasNext())
		{
			Bonus bonus = bonusIterator.next();
			if (player.intersects(bonus))
			{
				playerModel.setBonusUpgrade(bonus, player);

				//remove bonus object
				bonusIterator.remove();
			}
		}
	}

	//sets new set of bullets for every enemy ship on a list, triggered by Timer (controller)
	public void setNewBullets()
	{
		//create bullet for all objects on a list
		for (Enemy enemy : listOfEnemyShips)
		{
			//enemies create bullets only if they are not asteroids
			if (enemyModel.getIfEnemyAsteroid(enemy) == false)
			{
				Bullet newBullet = new Bullet();

				//sets bullet data
				bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
				bulletsModel.setPower(newBullet, 5);
				bulletsModel.calculateDeltas(newBullet, enemyModel.getCenter(enemy).x, enemyModel.getCenter(enemy).y,
						playerModel.getCenter(player).x, playerModel.getCenter(player).y );
				bulletsModel.setDeltas(newBullet, bulletsModel.getDeltaX(newBullet), bulletsModel.getDeltaY(newBullet));
				bulletsModel.setLocation(newBullet, enemyModel.getCenter(enemy).x, enemyModel.getCenter(enemy).y);
				bulletsModel.setSpeed(newBullet, 3);

				//adds bullet to list to paint
				listOfEnemyBullets.add(newBullet);
			}
		}
	}

	//methods to set new position of a bullets (triggered by Timer)
	public void setNewPositionOfBullets()
	{
		//update position of bullets and check if they are inside a scene
		updatePositionOfBullets();
		updatePositionOfPlayerBullets();
		checkIfBulletsOutOfScene();
	}

	//update position of a bullets of enemy ( triggered every Timer tick)
	public void updatePositionOfBullets()
	{
		//update for every enemy bullet on a list
		for (Bullet bulletToMove : listOfEnemyBullets)
		{	
			//take into consideration factors of steps on X and Y axis
			int deltaX = (int)(bulletsModel.getDeltaX(bulletToMove)*bulletsModel.getSpeed(bulletToMove));
			int deltaY = (int)(bulletsModel.getDeltaY(bulletToMove)*bulletsModel.getSpeed(bulletToMove));

			//define new location of bullet
			int x = bulletsModel.getLocation(bulletToMove).x + deltaX;
			int y = bulletsModel.getLocation(bulletToMove).y + deltaY;

			//sets new location of bullet
			bulletsModel.setLocation(bulletToMove, x, y);
		}
	}

	//checks if enemy and player bullets are inside a scene ( 4 borders)
	public void checkIfBulletsOutOfScene()
	{
		//checking bullets of Enemy
		ListIterator<Bullet> listOfEnemyBulletsIterator = listOfEnemyBullets.listIterator();
		while (listOfEnemyBulletsIterator.hasNext())
		{ 
			//remove if is out of one border
			Bullet bulletToCheck = listOfEnemyBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 645 || bulletToCheck.getLocation().y < 0
					||bulletToCheck.getLocation().x > 625 || bulletToCheck.getLocation().x < 0)
			{
				listOfEnemyBulletsIterator.remove();
			}
		}

		//checking bullets of Player
		ListIterator<Bullet> listOfPlayerBulletsIterator = listOfPlayerBullets.listIterator();
		while (listOfPlayerBulletsIterator.hasNext())
		{
			//remove if is out of one border
			Bullet bulletToCheck = listOfPlayerBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 645 || bulletToCheck.getLocation().y < 0
					||bulletToCheck.getLocation().x > 625 || bulletToCheck.getLocation().x < 0)
			{
				listOfPlayerBulletsIterator.remove();
			}
		}

	}

	//checks if enemy bullets are in collision with player object
	public void checkIfBulletInCollision(Player player)
	{
		ListIterator<Bullet> bulletIterator = listOfEnemyBullets.listIterator();
		while ( bulletIterator.hasNext())
		{
			Rectangle bulletRectangle =  bulletIterator.next().getBounds();

			//if player and enemy bullet collide, remove bullet and update player stats
			if (player.intersects(bulletRectangle))
			{
				bulletIterator.remove();
				playerModel.setShieldAndLife(player, -5, 0);
			}
		}
	}


	public void setNewBulletsOfPlayer()
	{
		String type = playerModel.getTypeOfWeapon(player);
		switch (type){
		case "MISSILES":
			setMisilesBullets();
			break;
		case "BLASTER":
			setBlasterBullets();
			break;
		case "LASER":
			setLaserBullets();
			break;
		case "BOMB":
			setBomb();
			break;
		default:
			setBlasterBullets();
			break;
		}
	}

	public void setMisilesBullets()
	{
		//create new missiles only if there are enemies on a scene and sets max number of missiles on scene to 20-30
		if (listOfEnemyShips.size() > 0 && listOfPlayerBullets.size() < 10+5*playerModel.getWeaponInfo(player, "numberOfMissiles"))
		{
			//sets new missile bullets -> max number of bullets is 4
			for (int i=0; i<playerModel.getWeaponInfo(player, "numberOfMissiles"); ++i)
			{
				//define bullet data
				Bullet newBullet = new Bullet();
				bulletsModel.setType(newBullet, "MISSILE");
				bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
				bulletsModel.setSpeed(newBullet, 6);
				bulletsModel.setPower(newBullet, playerModel.getWeaponInfo(player, "powerMissile"));

				//sets starting location
				if (i==0){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);}
				else if (i==1){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);}
				else if (i==2){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y-20);}
				else if (i==3){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y+20);}

				//add to list
				listOfPlayerBullets.add(newBullet);
			}
			//sets true-> missiles on a scene
			setIfContainsMissiles(true);

			//sets target for missiles
			setClosestEnemyToMissile();
		}
	}

	public void setBlasterBullets()
	{
		//sets new blaster bullets-> max number of bullets in one series is 5
		for (int i=0; i<playerModel.getWeaponInfo(player, "numberOfBlaster"); ++i)
		{
			//define bullet data
			Bullet newBullet = new Bullet();
			bulletsModel.setType(newBullet, "BLASTER");
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setSpeed(newBullet, 9);
			bulletsModel.setPower(newBullet, playerModel.getWeaponInfo(player, "powerBlaster"));

			//define bullets deltas ( X and Y step per Timers tick)
			if (i<3){bulletsModel.setDeltas(newBullet, 0, -1);}
			else if (i==3){bulletsModel.setDeltas(newBullet, 0.25, -1);}
			else if (i==4){bulletsModel.setDeltas(newBullet, -0.25, -1);}
			else if (i==5){bulletsModel.setDeltas(newBullet, 0.15, -1);}
			else if (i==6){bulletsModel.setDeltas(newBullet, -0.15, -1);}
			

			//sets starting location
			if (i == 0 || i ==3 || i==5){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);}
			else if (i==1 || i==4 || i==6){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);}
			else if (i==2){bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y-20);}

			//adds to list
			listOfPlayerBullets.add(newBullet);
		}
	}

	public void setLaserBullets()
	{
		//sets new blaster bullets-> max number of bullets in one series is 5
		for (int i=0; i<playerModel.getWeaponInfo(player, "numberOfLaser"); ++i)
		{
			//define bullet data
			Bullet newBullet = new Bullet();
			bulletsModel.setType(newBullet, "LASER");
			bulletsModel.setBulletSize(newBullet, new Dimension(6,playerModel.getNewPosition(player).y+20));
			bulletsModel.setSpeed(newBullet, 0);
			bulletsModel.setPower(newBullet, playerModel.getWeaponInfo(player, "powerLaser"));

			//sets starting location
			if (i == 0)
			{
				bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x,
						playerModel.getCenter(player).y - bulletsModel.getSize(newBullet).height-20);
				bulletsModel.setLocationAsLaser(newBullet, new Point(0,-bulletsModel.getSize(newBullet).height-20));
			}
			else if (i==1)
			{
				bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x-10,
						playerModel.getCenter(player).y - bulletsModel.getSize(newBullet).height);
				bulletsModel.setLocationAsLaser(newBullet, new Point(-10,-bulletsModel.getSize(newBullet).height));
			}
			else if (i==2)
			{
				bulletsModel.setLocation(newBullet, playerModel.getCenter(player).x+10,
						playerModel.getCenter(player).y - bulletsModel.getSize(newBullet).height);
				bulletsModel.setLocationAsLaser(newBullet, new Point(10,-bulletsModel.getSize(newBullet).height));
			}

			//adds to list
			listOfPlayerBullets.add(newBullet);
		}
	}

	//clears scene from enemies and their bullets, can be used limited amount of time
	public void setBomb()
	{
		if (playerModel.getWeaponInfo(player, "numberOfBombs") > 0)
		{
			//remove bullets from scene
			ListIterator<Bullet> bulletIterator = listOfEnemyBullets.listIterator();
			while (bulletIterator.hasNext())
			{
				bulletIterator.next();
				bulletIterator.remove();
			}

			//remove enemies from scene
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
			while (enemyIterator.hasNext())
			{
				enemyIterator.next();
				enemyIterator.remove();
			}

			//reduce number of bombs carried by player by 1 ( player has max 3 bombs)
			playerModel.setNumberOfBombs(player, -1);
		}
	}

	//update position of player bullets
	public void updatePositionOfPlayerBullets()
	{
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();
		while (bulletIterator.hasNext())
		{
			Bullet bulletToMove = bulletIterator.next();
			if (bulletsModel.getType(bulletToMove) == "MISSILE")
			{
				bulletsModel.calculateMovementAsAMissile(bulletToMove, bulletsModel.getTargetOfMissile(bulletToMove));
			}

			if (bulletsModel.getType(bulletToMove) == "MISSILE" || bulletsModel.getType(bulletToMove) == "BLASTER")
			{
				updatePositionAsBlasterMissile(bulletToMove);
			}
			else if (bulletsModel.getType(bulletToMove) == "LASER")
			{
				updatePositionAsLaser(bulletToMove);
				bulletsModel.setTime(bulletToMove, timerDelay);
				if (bulletsModel.getTime(bulletToMove) > 2000)
				{
					bulletIterator.remove();
				}
			}
		}
	}

	public void updatePositionAsBlasterMissile(Bullet bulletToMove)
	{
		//takes into consideration factor step for X and Y axis
		int stepX = (int)(bulletsModel.getSpeed(bulletToMove)*bulletsModel.getDeltaX(bulletToMove));
		int stepY = (int)(bulletsModel.getSpeed(bulletToMove)*bulletsModel.getDeltaY(bulletToMove));

		//calculate new position
		int x = bulletsModel.getLocation(bulletToMove).x + stepX;
		int y = bulletsModel.getLocation(bulletToMove).y + stepY;

		//sets new position
		bulletsModel.setLocation(bulletToMove, x, y);
	}

	public void updatePositionAsLaser(Bullet bulletToMove)
	{
		if (bulletToMove.getIsInCollision() == false) resizeLaser(bulletToMove);

		int x = playerModel.getCenter(player).x + bulletsModel.getLocationAsLaser(bulletToMove).x;
		int y = playerModel.getCenter(player).y + bulletsModel.getLocationAsLaser(bulletToMove).y;

		bulletsModel.setLocation(bulletToMove, x, y);
	}

	public void resizeLaser(Bullet bullet)
	{
		bulletsModel.setBulletSize(bullet, new Dimension(6,playerModel.getNewPosition(player).y+20));
		bulletsModel.setLocationAsLaser(bullet, new Point(bulletsModel.getLocationAsLaser(bullet).x,
				-playerModel.getNewPosition(player).y-20));
	}

	//checks if bullets of player collide with enemy
	public void checkIfPlayerBulletInCollision()
	{
		//assign iterator to player bullets list
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();

		while ( bulletIterator.hasNext())
		{
			ifLaserInCollision = false;

			Bullet bullet =  bulletIterator.next();

			//assign iterator to enemy ships list
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();

			while(enemyIterator.hasNext())
			{
				Enemy enemy = enemyIterator.next();

				//if player bullet collide with enemy object
				if (bullet.getBounds().intersects(enemy.getBounds()))
				{
					if (bulletsModel.getType(bullet) == "LASER")
					{
						updateSizeOfLaser(bullet, enemy);
						bullet.setIsInCollision(true);
					}
					//subtract enemy life by players bullet power
					updateEnemyLife(enemy, bulletsModel.getPower(bullet));
					//check if enemy object destroyed, if yes, remove
					checkIfDestroyEnemy(enemyIterator, enemy);
					if (bulletsModel.getType(bullet) == "BLASTER" || bulletsModel.getType(bullet) == "MISSILE")
					{
						//remove bullet
						bulletIterator.remove();
					}
					else if (bulletsModel.getTime(bullet) > 2000)
					{
						//remove bullet
						bulletIterator.remove();
					}
					break;
				}
				else if (bulletsModel.getType(bullet) == "LASER")
				{
					bulletsModel.setIsInCollision(bullet, false);
				}
			}
		}
	}

	//sets new value of enemy ship object after hit by a players bullet
	public void updateEnemyLife(Enemy enemy, int bulletPower)
	{
		enemyModel.updateEnemyLife(enemy, bulletPower);
		enemyModel.setIfIsDestroyed(enemy);
	}

	//checks if enemy ship should be destroyed and removed from scene
	public void checkIfDestroyEnemy(ListIterator<Enemy> enemyIterator, Enemy enemy)
	{
		//only if enemy life is lower than zero
		if (enemyModel.getIsDestroyed(enemy) == true)
		{
			//remove enemy object
			enemyIterator.remove();
			//update points and shield of player
			updatePlayerStats();
		}
	}

	public void updateSizeOfLaser(Bullet bullet, Enemy enemy)
	{	
		int distance = (int) enemy.getCenterY();
		bulletsModel.setBulletSize(bullet, new Dimension(6,playerModel.getNewPosition(player).y+20 - distance));
		bulletsModel.setLocationAsLaser(bullet, new Point(bulletsModel.getLocationAsLaser(bullet).x,
				-bulletsModel.getSize(bullet).height));

		int x = playerModel.getCenter(player).x + bulletsModel.getLocationAsLaser(bullet).x;
		int y = playerModel.getCenter(player).y + bulletsModel.getLocationAsLaser(bullet).y;

		bulletsModel.setLocation(bullet, x, y);
	}

	//update current lvl of game
	public void updateLevelOfGame()
	{
		//sets current lvl of game
		if (playerModel.getPlayersPoints(player) < 50)
		{
			lvlOfGame = 1;
		}
		else
		{
			lvlOfGame= playerModel.getPlayersPoints(player)/50+1;
		}
	}

	//update stats of player after destruction of an enemy
	public void updatePlayerStats()
	{
		playerModel.setShieldAndLife(player, 5, 0);
		playerModel.updatePoints(player, 1);
		updateLevelOfGame();
	}

	//GETTERS
	//--------------------------------------------------------------------------------------------------------

	//gets player object
	public Player getPlayer()
	{
		return player;
	}

	//gets player model object
	public PlayerModel getPlayerModel()
	{
		return playerModel;
	}

	//gets list contains enemy ships
	public ArrayList<Enemy> getListOfEnemyShips()
	{
		return listOfEnemyShips;
	}

	//gets list contains enemy bullets
	public ArrayList<Bullet> getListOfEnemyBullets()
	{
		return listOfEnemyBullets;
	}

	//gets list contains player bullets
	public ArrayList<Bullet> getListOfPlayerBullets()
	{
		return listOfPlayerBullets;
	}

	//gets list of bonuses
	public ArrayList<Bonus> getListOfBonuses()
	{
		return listOfBonuses;
	}

	//gets lvl of game
	public int getLvlOfGame()
	{
		return lvlOfGame;
	}
}
