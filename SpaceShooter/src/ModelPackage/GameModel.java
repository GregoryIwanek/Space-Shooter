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

	static private int lvlOfGame = 1;
	static private boolean ifContainsMissiles = false;

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

	//sets type of chosen by player weapon (missiles, blaster, bomb etc)
	public void setTypeOfPlayerWeapon (String typeOfWeapon)
	{
		playerModel.setTypeOfWeapon(player, typeOfWeapon);
	}

	//sets true if player shoot missiles and they are present on a scene
	public void setIfContainsMissiles(boolean ifContainsMissiles)
	{
		GameModel.ifContainsMissiles = ifContainsMissiles;
	}

	//checks if given bullet is a MISSILE, update status if yes
	public void checkIfMissilesOnScene(Bullet bullet)
	{
		if (bulletsModel.getTypeOfBullet(bullet) == "MISSILE")
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
		if (bullet.getTypeOfBullet() == "MISSILE")
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
		playerModel.setImageOfPlayer(player, "/Player.png");
		playerModel.setSizeOfPlayer(player, new Dimension(45,65));
	}

	//sets new enemy ship, define information and adds to list of objects to paint
	public void setNewEnemyShip()
	{
		Enemy newEnemy = new Enemy();

		//sets enemy object data
		enemyModel.setEnemySize(newEnemy, new Dimension(35,50));
		enemyModel.setImageOfEnemy(newEnemy, "/Enemy_ship_1.png");
		enemyModel.setNewPosition(newEnemy, enemyModel.getRandomStartingPos(), 0);
		enemyModel.setEnemyLife(newEnemy, 800+lvlOfGame*200);
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
		enemyModel.setImageOfEnemy(newEnemy, "/rock.png");
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
		bonusModel.rollTypeOfNumberBonus(bonus);
		bonusModel.setNewPosition(bonus, bonusModel.getRandomStartingPos(), 0);
		bonusModel.setImageOfBonus(bonus);

		//adds to painting list
		listOfBonuses.add(bonus);
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
			if (enemyModel.getPositionOfEnemy(enemy).y < 700) //650 is a bottom of a panel
			{
				enemyModel.setNewPosition(enemy, enemyModel.getPositionOfEnemy(enemy).x,
						enemyModel.getPositionOfEnemy(enemy).y+enemyModel.getSpeedOfEnemy(enemy));
			}
		}

		//update position of every bonus on a list
		for (Bonus bonus : listOfBonuses)
		{
			if (bonusModel.getPositionOfBonus(bonus).y < 700)
			{
				bonusModel.setNewPosition(bonus, bonusModel.getPositionOfBonus(bonus).x, bonusModel.getPositionOfBonus(bonus).y+1);
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
			if (enemyModel.getPositionOfEnemy(enemyToCheck).y > 640) //650 bottom of panel
			{
				listOfEnemyShipsIterator.remove();
			}
		}

		ListIterator<Bonus> listOfBonusesIterator = listOfBonuses.listIterator();
		while (listOfBonusesIterator.hasNext())
		{
			Bonus bonusToCheck = listOfBonusesIterator.next();
			if (bonusModel.getPositionOfBonus(bonusToCheck).y > 640)
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
				playerModel.setShieldToDisplay(player, -25);
				playerModel.setLifeToDisplay(player, -25);
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
				bulletsModel.setPowerOfBullet(newBullet, 5);
				bulletsModel.calculateDeltasOfBullet(newBullet, enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y,
						playerModel.getCenter(player).x, playerModel.getCenter(player).y );
				bulletsModel.setDeltasOfBullet(newBullet, bulletsModel.getDeltaXOfBullet(newBullet), bulletsModel.getDeltaYOfBullet(newBullet));
				bulletsModel.setLocationOfBullet(newBullet, enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y);
				bulletsModel.setSpeedOfBullet(newBullet, 3);

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
			int deltaX = (int)(bulletsModel.getDeltaXOfBullet(bulletToMove)*bulletsModel.getSpeedOfBullet(bulletToMove));
			int deltaY = (int)(bulletsModel.getDeltaYOfBullet(bulletToMove)*bulletsModel.getSpeedOfBullet(bulletToMove));

			//define new location of bullet
			int x = bulletsModel.getLocationOfBullet(bulletToMove).x + deltaX;
			int y = bulletsModel.getLocationOfBullet(bulletToMove).y + deltaY;

			//sets new location of bullet
			bulletsModel.setLocationOfBullet(bulletToMove, x, y);
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
				playerModel.setShieldToDisplay(player, -5);
				playerModel.setLifeToDisplay(player, -5);
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
				bulletsModel.setTypeOfBullet(newBullet, "MISSILE");
				bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
				bulletsModel.setSpeedOfBullet(newBullet, 6);
				bulletsModel.setPowerOfBullet(newBullet, playerModel.getWeaponInfo(player, "powerMissile"));

				//sets starting location
				if (i==0){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);}
				else if (i==1){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);}
				else if (i==2){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y-20);}
				else if (i==3){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y+20);}

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
			bulletsModel.setTypeOfBullet(newBullet, "BLASTER");
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setSpeedOfBullet(newBullet, 9);
			bulletsModel.setPowerOfBullet(newBullet, playerModel.getWeaponInfo(player, "powerBlaster"));

			//define bullets deltas ( X and Y step per Timers tick)
			if (i<3){bulletsModel.setDeltasOfBullet(newBullet, 0, -1);}
			else if (i==3){bulletsModel.setDeltasOfBullet(newBullet, 0.25, -1);}
			else if (i==4){bulletsModel.setDeltasOfBullet(newBullet, -0.25, -1);}

			//sets starting location
			if (i == 0 || i ==3){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);}
			else if (i==1 || i==4){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);}
			else if (i==2){bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x, playerModel.getCenter(player).y-20);}

			//adds to list
			listOfPlayerBullets.add(newBullet);
		}
	}

	public void setLaserBullets()
	{
		//sets new blaster bullets-> max number of bullets in one series is 5
		for (int i=0; i<3; ++i)
		{
			//define bullet data
			Bullet newBullet = new Bullet();
			bulletsModel.setTypeOfBullet(newBullet, "LASER");
			bulletsModel.setBulletSize(newBullet, new Dimension(6,playerModel.getNewPosition(player).y));
			bulletsModel.setSpeedOfBullet(newBullet, 0);
			bulletsModel.setPowerOfBullet(newBullet, playerModel.getWeaponInfo(player, "powerLaser"));

			//sets starting location
			if (i == 0)
			{
				bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x+10,
						playerModel.getCenter(player).y - bulletsModel.getSizeOfBullet(newBullet).height);
				bulletsModel.setLocationAsLaser(newBullet, new Point(10,-bulletsModel.getSizeOfBullet(newBullet).height));
			}
			else if (i==1)
			{
				bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-10,
						playerModel.getCenter(player).y - bulletsModel.getSizeOfBullet(newBullet).height);
				bulletsModel.setLocationAsLaser(newBullet, new Point(-10,-bulletsModel.getSizeOfBullet(newBullet).height));
			}
			else if (i==2)
			{
				bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x,
						playerModel.getCenter(player).y - bulletsModel.getSizeOfBullet(newBullet).height-20);
				bulletsModel.setLocationAsLaser(newBullet, new Point(0,-bulletsModel.getSizeOfBullet(newBullet).height-20));
			}

			//adds to list
			listOfPlayerBullets.add(newBullet);
		}
	}

	//clears scene from enemies and their bullets, can be used limited amount of time
	public void setBomb()
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
	}

	//update position of player bullets
	public void updatePositionOfPlayerBullets()
	{
		//for all player bullets updates position
		for (Bullet bulletToMove : listOfPlayerBullets)
		{
			if (bulletsModel.getTypeOfBullet(bulletToMove) == "MISSILE")
			{
				bulletsModel.calculateMovementAsAMissile(bulletToMove, bulletsModel.getTargetOfMissile(bulletToMove));
			}

			if (bulletsModel.getTypeOfBullet(bulletToMove) == "MISSILE" || bulletsModel.getTypeOfBullet(bulletToMove) == "BLASTER")
			{
				updatePositionAsBlasterMissile(bulletToMove);
			}
			else if (bulletsModel.getTypeOfBullet(bulletToMove) == "LASER")
			{
				updatePositionAsLaser(bulletToMove);
			}
		}
	}

	public void updatePositionAsBlasterMissile(Bullet bulletToMove)
	{
		//takes into consideration factor step for X and Y axis
		int stepX = (int)(bulletsModel.getSpeedOfBullet(bulletToMove)*bulletsModel.getDeltaXOfBullet(bulletToMove));
		int stepY = (int)(bulletsModel.getSpeedOfBullet(bulletToMove)*bulletsModel.getDeltaYOfBullet(bulletToMove));

		//calculate new position
		int x = bulletsModel.getLocationOfBullet(bulletToMove).x + stepX;
		int y = bulletsModel.getLocationOfBullet(bulletToMove).y + stepY;

		//sets new position
		bulletsModel.setLocationOfBullet(bulletToMove, x, y);
	}

	public void updatePositionAsLaser(Bullet bulletToMove)
	{
		resizeLaser(bulletToMove);

		int x = playerModel.getCenter(player).x + bulletsModel.getLocationAsLaser(bulletToMove).x;
		int y = playerModel.getCenter(player).y + bulletsModel.getLocationAsLaser(bulletToMove).y;

		bulletsModel.setLocationOfBullet(bulletToMove, x, y);
	}

	public void resizeLaser(Bullet bullet)
	{
		bulletsModel.setBulletSize(bullet, new Dimension(6,playerModel.getNewPosition(player).y));
		bulletsModel.setLocationAsLaser(bullet, new Point(bulletsModel.getLocationAsLaser(bullet).x,
				-playerModel.getNewPosition(player).y));
	}

	//checks if bullets of player collide with enemy
	public void checkIfPlayerBulletInCollision()
	{
		//assign iterator to player bullets list
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();

		while ( bulletIterator.hasNext())
		{
			Bullet bullet =  bulletIterator.next();

			//assign iterator to enemy ships list
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();

			while(enemyIterator.hasNext())
			{
				Enemy enemy = enemyIterator.next();

				//if player bullet collide with enemy object
				if (bullet.getBounds().intersects(enemy.getBounds()))
				{
					if (bulletsModel.getTypeOfBullet(bullet) == "LASER")
					{
						updateSizeOfLaser(bullet, enemy);
					}
					//subtract enemy life by players bullet power
					updateEnemyLife(enemy, bulletsModel.getPowerOfBullet(bullet));
					//check if enemy object destroyed, if yes, remove
					checkIfDestroyEnemy(enemyIterator, enemy);
					//remove bullet
					//bulletIterator.remove();
					break;
				}
			}
		}
	}

	//sets new value of enemy ship object after hit by a players bullet
	public void updateEnemyLife(Enemy enemy, int bulletPower)
	{
		enemyModel.updateEnemyLife(enemy, bulletPower);
	}

	//checks if enemy ship should be destroyed and removed from scene
	public void checkIfDestroyEnemy(ListIterator<Enemy> enemyIterator, Enemy enemy)
	{
		//only if enemy life is lower than zero
		if (enemy.isDestroyed() == true)
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
		bulletsModel.setBulletSize(bullet, new Dimension(6,bulletsModel.getLocationAsLaser(bullet).y - distance));
		bulletsModel.setLocationAsLaser(bullet, new Point(bulletsModel.getLocationAsLaser(bullet).x,
				-bulletsModel.getSizeOfBullet(bullet).height));

		int x = playerModel.getCenter(player).x + bulletsModel.getLocationAsLaser(bullet).x;
		int y = playerModel.getCenter(player).y + bulletsModel.getLocationAsLaser(bullet).y;

		bulletsModel.setLocationOfBullet(bullet, x, y);
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
		playerModel.setShieldToDisplay(player, 5);
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
