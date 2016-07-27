package ModelPackage;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ListIterator;

public class GameModel 
{
	static private PlayerModel playerModel;
	static private EnemyModel enemyModel;
	private BulletsModel bulletsModel;
	static private Player player;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfEnemyBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;

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
		
		//initiate lists
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfEnemyBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
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
		
		//adds to painting list 
		listOfEnemyShips.add(newEnemy);
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
				enemyModel.setNewPosition(enemy, enemyModel.getPositionOfEnemy(enemy).x, enemyModel.getPositionOfEnemy(enemy).y+1);
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
			if (enemyModel.getPositionOfEnemy(enemyToCheck).y > 500) //660
			{
				listOfEnemyShipsIterator.remove();
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
	}

	//sets new set of bullets for every enemy ship on a list, triggered by Timer (controller)
	public void setNewBullets()
	{
		//create bullet for all objects on a list
		for (Enemy enemy : listOfEnemyShips)
		{
			Bullet newBullet = new Bullet();
			
			//sets bullet data
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setPowerOfBullet(newBullet, 5);
			bulletsModel.calculateDeltasOfBullet(newBullet, enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y,
					playerModel.getCenter(player).x, playerModel.getCenter(player).y );
			bulletsModel.setDeltasOfBullet(newBullet, bulletsModel.getDeltaXOfBullet(newBullet), bulletsModel.getDeltaYOfBullet(newBullet));
			bulletsModel.setLocationOfBullet(newBullet, enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y);
			
			//adds bullet to list to paint
			listOfEnemyBullets.add(newBullet);
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
			int deltaX = (int)(bulletsModel.getDeltaXOfBullet(bulletToMove)*3);
			int deltaY = (int)(bulletsModel.getDeltaYOfBullet(bulletToMove)*3);
			
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
			if (bulletToCheck.getLocation().y > 600 || bulletToCheck.getLocation().y < 50 //650 0
					||bulletToCheck.getLocation().x > 600 || bulletToCheck.getLocation().x < 50) //630 0
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
			if (bulletToCheck.getLocation().y > 600 || bulletToCheck.getLocation().y < 50
					||bulletToCheck.getLocation().x > 600 || bulletToCheck.getLocation().x < 50)
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
		int type = 1;
		switch (type){
		case 1:
			setMisilesBullets();
			break;
		case 2:
			setDefinitionBlasterBullets();
			break;
		case 3:
			setLaserBullets();
			break;
		case 4:
			setBombBullets();
			break;
		default:
			setDefinitionBlasterBullets();
			break;
		}
		Bullet newBulletLeft = new Bullet();
		bulletsModel.setBulletSize(newBulletLeft, new Dimension(6,6));
		bulletsModel.setPowerOfBullet(newBulletLeft, 500);
		bulletsModel.setDeltasOfBullet(newBulletLeft, 0, 1);
		bulletsModel.setLocationOfBullet(newBulletLeft, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);
		listOfPlayerBullets.add(newBulletLeft);

		Bullet newBulletRight = new Bullet();
		bulletsModel.setBulletSize(newBulletRight, new Dimension(6,6));
		bulletsModel.setPowerOfBullet(newBulletRight, 500);
		bulletsModel.setDeltasOfBullet(newBulletRight, 0, 1);
		bulletsModel.setLocationOfBullet(newBulletRight, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);
		listOfPlayerBullets.add(newBulletRight);
	}
	
	public void setMisilesBullets()
	{

	}
	
	public void setDefinitionBlasterBullets()
	{
		int levelOfWeapon = player.getLevelOfWeapon("lvlOfBlaster");
		switch (levelOfWeapon)
		{
		case 1:
			setNewBlasterBullets(2, 500, 0, 1);
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
	}
	
	public void setNewBlasterBullets(int number, int power, double deltaX, double deltaY)
	{
		for (int i=0; i<2; ++i)
		{
			Bullet newBullet = new Bullet();
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setPowerOfBullet(newBullet, power);
			bulletsModel.setDeltasOfBullet(newBullet, deltaX, deltaY);
			listOfPlayerBullets.add(newBullet);
			if (i == 0)  bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);
			else  bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);
		}

		for (int i=0; i<2; ++i)
		{
			Bullet newBullet = new Bullet();
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setPowerOfBullet(newBullet, power);
			bulletsModel.setDeltasOfBullet(newBullet, deltaX, deltaY);
			listOfPlayerBullets.add(newBullet);
			if (i == 0) bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-5, playerModel.getCenter(player).y-5); 
			else bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-5, playerModel.getCenter(player).y+5);
		}
	}
	
	public void setLaserBullets()
	{

	}
	
	public void setBombBullets()
	{

	}
	
	//update position of player bullets
	public void updatePositionOfPlayerBullets()
	{
		//for all player bullets updates position
		for (Bullet bulletToMove : listOfPlayerBullets)
		{
			//takes into consideration factor step for X and Y axis
			int stepX = (int)(8 *bulletsModel.getDeltaXOfBullet(bulletToMove));
			int stepY = (int)(8*bulletsModel.getDeltaYOfBullet(bulletToMove));
			
			//calculate new position
			int x = bulletsModel.getLocationOfBullet(bulletToMove).x + stepX;
			int y = bulletsModel.getLocationOfBullet(bulletToMove).y-8;
			
			//sets new position
			bulletsModel.setLocationOfBullet(bulletToMove, x, y);
		}
	}
	
	//checks if bullets of player collide with enemy
	public void checkIfPlayerBulletInCollision()
	{
		//assign iterator to player bullets list
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();
		
		while ( bulletIterator.hasNext())
		{
			Bullet bulletRectangle =  bulletIterator.next();
			
			//assign iterator to enemy ships list
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
			
			while(enemyIterator.hasNext())
			{
				Enemy enemyRectangle = enemyIterator.next();
				
				//if player bullet collide with enemy object
				if (bulletRectangle.getBounds().intersects(enemyRectangle.getBounds()))
				{
					//subtract enemy life by players bullet power
					updateEnemyLife(enemyRectangle, bulletsModel.getPowerOfBullet(bulletRectangle));
					//remove bullet
					bulletIterator.remove();	
					//check if enemy object destroyed, if yes, remove
					checkIfDestroyEnemy(enemyIterator, enemyRectangle);
				}
			}
		}
	}
	
	//sets new value of enemy ship object after hit by a players bullet
	public void updateEnemyLife(Enemy enemy, int bulletPower)
	{
		enemy.updateLife(bulletPower);
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
	
	//update stats of player after destruction of an enemy
	public void updatePlayerStats()
	{
		playerModel.setShieldToDisplay(player, 5);
		playerModel.updatePoints(player, 1);
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
}
