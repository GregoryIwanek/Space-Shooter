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
	static private ArrayList<Bullet> listOfBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;

	public GameModel()
	{
		setDefinitionOfData();
	}
	public void setDefinitionOfData()
	{
		player = new Player();
		playerModel = new PlayerModel();
		enemyModel = new EnemyModel();
		bulletsModel = new BulletsModel();
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
		playerModel.setImageOfPlayer(player, "/Player.png");
		playerModel.setSizeOfPlayer(player, new Dimension(45,65));
	}

	public void setNewEnemyShip()
	{
		Enemy newEnemy = new Enemy();
		enemyModel.setEnemySize(newEnemy, new Dimension(35,50));
		enemyModel.setImageOfEnemy(newEnemy, "/Enemy_ship_1.png");
		enemyModel.setNewPosition(newEnemy, enemyModel.getRandomStartingPos(), 0);
		listOfEnemyShips.add(newEnemy);
	}
	public void setNewPositionOfShips()
	{
		updatePositionOfShips();
		checkIfEnemyOutOfScene();
	}
	public void updatePositionOfShips()
	{
		for (Enemy enemy : listOfEnemyShips)
		{
			if (enemy.getLocation().y < 700) //650 is a bottom of a panel
			{
				enemy.setLocation(enemy.getLocation().x, enemy.getLocation().y+1);
			}
		}
	}
	public void checkIfEnemyOutOfScene()
	{
		ListIterator<Enemy> listOfEnemyShipsIterator = listOfEnemyShips.listIterator();
		while (listOfEnemyShipsIterator.hasNext())
		{ 
			Enemy enemyToCheck = listOfEnemyShipsIterator.next();
			if (enemyToCheck.getLocation().y > 500) //660
			{
				listOfEnemyShipsIterator.remove();
			}
		}
	}
	public void checkIfEnemyInCollision(Player player)
	{
		ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
		while (enemyIterator.hasNext())
		{
			Rectangle enemyRectangle = enemyIterator.next().getBounds();
			if (player.intersects(enemyRectangle))
			{	
				enemyIterator.remove();
				playerModel.setShieldToDisplay(player, -25);
				playerModel.setLifeToDisplay(player, -25);
			}
		}
	}

	public void setNewBullets()
	{
		for (Enemy enemy : listOfEnemyShips)
		{
			Bullet newBullet = new Bullet();
			//bulletsModel.setImageOfBullet(newBullet, "/Bullet.png");
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			newBullet.setPowerOfBullet(5);
			listOfBullets.add(newBullet);
			bulletsModel.calculateMovementOfBullet(enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y,
					playerModel.getCenter(player).x, playerModel.getCenter(player).y );
			newBullet.setDeltasOfBullet(bulletsModel.deltaX, bulletsModel.deltaY);
			newBullet.setLocation(enemyModel.getCenterOfEnemy(enemy).x, enemyModel.getCenterOfEnemy(enemy).y);
		}
	}
	public void setNewPositionOfBullets()
	{
		updatePositionOfBullets();
		updatePositionOfPlayerBullets();
		checkIfBulletsOutOfScene();
	}
	public void updatePositionOfBullets()
	{
		for (Bullet bulletToMove : listOfBullets)
		{	
			int deltaX = (int)(bulletsModel.getDeltaXOfBullet(bulletToMove)*3);
			int deltaY = (int)(bulletsModel.getDeltaYOfBullet(bulletToMove)*3);
			int x = bulletsModel.getLocationOfBullet(bulletToMove).x + deltaX;
			int y = bulletsModel.getLocationOfBullet(bulletToMove).y + deltaY;
			bulletsModel.setLocationOfBullet(bulletToMove, x, y);
		}
	}
	public void checkIfBulletsOutOfScene()
	{
		//checking bullets of Enemy
		ListIterator<Bullet> listOfBulletsIterator = listOfBullets.listIterator();
		while (listOfBulletsIterator.hasNext())
		{ 
			Bullet bulletToCheck = listOfBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 600 || bulletToCheck.getLocation().y < 50 //650 0
					||bulletToCheck.getLocation().x > 600 || bulletToCheck.getLocation().x < 50) //630 0
			{
				listOfBulletsIterator.remove();
			}
		}
		//checking bullets of Player
		ListIterator<Bullet> listOfPlayerBulletsIterator = listOfPlayerBullets.listIterator();
		while (listOfPlayerBulletsIterator.hasNext())
		{
			Bullet bulletToCheck = listOfPlayerBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 600 || bulletToCheck.getLocation().y < 50
					||bulletToCheck.getLocation().x > 600 || bulletToCheck.getLocation().x < 50)
			{
				listOfPlayerBulletsIterator.remove();
			}
		}

	}
	public void checkIfBulletInCollision(Player player)
	{
		ListIterator<Bullet> bulletIterator = listOfBullets.listIterator();
		while ( bulletIterator.hasNext())
		{
			Rectangle bulletRectangle =  bulletIterator.next().getBounds();
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
		//bulletsModel.setImageOfBullet(newBulletLeft, "/BulletBlaster.png");
		bulletsModel.setDeltasOfBullet(newBulletLeft, 0, 1);
		bulletsModel.setLocationOfBullet(newBulletLeft, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);
		listOfPlayerBullets.add(newBulletLeft);

		Bullet newBulletRight = new Bullet();
		bulletsModel.setBulletSize(newBulletRight, new Dimension(6,6));
		bulletsModel.setPowerOfBullet(newBulletRight, 500);
		//bulletsModel.setImageOfBullet(newBulletRight, "/BulletBlaster.png");
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
			//bulletsModel.setImageOfBullet(newBullet, "/BulletBlaster.png");
			listOfPlayerBullets.add(newBullet);
			if (i == 0)  bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);
			else  bulletsModel.setLocationOfBullet(newBullet, playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);
		}

		for (int i=0; i<2; ++i)
		{
			Bullet newBullet = new Bullet();
			bulletsModel.setBulletSize(newBullet, new Dimension(6,6));
			bulletsModel.setPowerOfBullet(newBullet, power);
			//bulletsModel.setImageOfBullet(newBullet, "/BulletBlaster.png");
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
	public void updatePositionOfPlayerBullets()
	{
		for (Bullet bulletToMove : listOfPlayerBullets)
		{
			int stepX = (int)(8 *bulletsModel.getDeltaXOfBullet(bulletToMove));
			int stepY = (int)(8*bulletsModel.getDeltaYOfBullet(bulletToMove));
			int x = bulletsModel.getLocationOfBullet(bulletToMove).x + stepX;
			int y = bulletsModel.getLocationOfBullet(bulletToMove).y-8;
			bulletsModel.setLocationOfBullet(bulletToMove, x, y);
		}
	}
	public void checkIfPlayerBulletInCollision()
	{
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();
		while ( bulletIterator.hasNext())
		{
			Bullet bulletRectangle =  bulletIterator.next();
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
			while(enemyIterator.hasNext())
			{
				Enemy enemyRectangle = enemyIterator.next();
				if (bulletRectangle.getBounds().intersects(enemyRectangle.getBounds()))
				{
					updateEnemyLife(enemyRectangle, bulletsModel.getPowerOfBullet(bulletRectangle));
					bulletIterator.remove();
					checkIfDestroyEnemy(enemyIterator, enemyRectangle);
				}
			}
		}
	}
	public void updateEnemyLife(Enemy enemy, int bulletPower)
	{
		enemy.updateLife(bulletPower);
	}
	public void checkIfDestroyEnemy(ListIterator<Enemy> enemyIterator, Enemy enemy)
	{
		if (enemy.isDestroyed() == true)
		{
			enemyIterator.remove();
			updatePlayerStats();
		}
	}
	public void updatePlayerStats()
	{
		playerModel.setShieldToDisplay(player, 5);
		playerModel.updatePoints(player, 1);
	}
	//GETTERS
	//--------------------------------------------------------------------------------------------------------
	public Player getPlayer()
	{
		return player;
	}
	public PlayerModel getPlayerModel()
	{
		return playerModel;
	}
	public ArrayList<Enemy> getListOfEnemyShips()
	{
		return listOfEnemyShips;
	}
	public ArrayList<Bullet> getListOfBullets()
	{
		return listOfBullets;
	}
	public ArrayList<Bullet> getListOfPlayerBullets()
	{
		return listOfPlayerBullets;
	}
}
