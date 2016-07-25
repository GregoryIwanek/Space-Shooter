package ModelPackage;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
		playerModel = new PlayerModel();
		player = new Player();
		enemyModel = new EnemyModel(200,200);
		bulletsModel = new BulletsModel();
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
	}
	public void setPlayerModel(int x, int y)
	{

	}

	public void setNewEnemyShip()
	{
		Enemy newEnemy = new Enemy();
		listOfEnemyShips.add(newEnemy);
		newEnemy.setLocation(enemyModel.getRandomStartingPos(),0);
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
			if (enemy.getLocation().y < 550) //650 is a bottom of a panel
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
			if (enemyToCheck.getLocation().y > 500)
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
			newBullet.setImageOfBullet("/Bullet.png");
			newBullet.setPowerOfBullet(5);
			listOfBullets.add(newBullet);
			bulletsModel.calculateMovementOfBullet(enemy.getCenter().x, enemy.getCenter().y,
					playerModel.getCenter(player).x, playerModel.getCenter(player).y );
			newBullet.setDeltasOfBullet(bulletsModel.deltaX, bulletsModel.deltaY);
			newBullet.setLocation(enemy.getCenter().x, enemy.getCenter().y);
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
			int deltaX = (int)(bulletToMove.deltaX*3);
			int deltaY = (int)(bulletToMove.deltaY*3);
			bulletToMove.setLocation(bulletToMove.getLocation().x+deltaX, 
					bulletToMove.getLocation().y+deltaY);
		}
	}
	public void checkIfBulletsOutOfScene()
	{
		//checking bullets of Enemy
		ListIterator<Bullet> listOfBulletsIterator = listOfBullets.listIterator();
		while (listOfBulletsIterator.hasNext())
		{ 
			Bullet bulletToCheck = listOfBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 580 || bulletToCheck.getLocation().y < 0 
					||bulletToCheck.getLocation().x > 580 || bulletToCheck.getLocation().x < 0)
			{
				listOfBulletsIterator.remove();
			}
		}
		//checking bullets of Player
		ListIterator<Bullet> listOfPlayerBulletsIterator = listOfPlayerBullets.listIterator();
		while (listOfPlayerBulletsIterator.hasNext())
		{
			Bullet bulletToCheck = listOfPlayerBulletsIterator.next();
			if (bulletToCheck.getLocation().y > 580 || bulletToCheck.getLocation().y < 0 
					||bulletToCheck.getLocation().x > 580 || bulletToCheck.getLocation().x < 0)
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
		newBulletLeft.setPowerOfBullet(500);
		newBulletLeft.setImageOfBullet("/BulletBlaster.png");
		listOfPlayerBullets.add(newBulletLeft);
		newBulletLeft.setDeltasOfBullet(0, 1);
		newBulletLeft.setLocation(playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);

		Bullet newBulletRight = new Bullet();
		newBulletRight.setPowerOfBullet(500);
		newBulletRight.setImageOfBullet("/BulletBlaster.png");
		listOfPlayerBullets.add(newBulletRight);
		newBulletRight.setDeltasOfBullet(0, 1);
		newBulletRight.setLocation(playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);
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
			newBullet.setPowerOfBullet(power);
			newBullet.setImageOfBullet("/BulletBlaster.png");
			listOfPlayerBullets.add(newBullet);
			newBullet.setDeltasOfBullet(deltaX, deltaY);
			if (i == 0) newBullet.setLocation(playerModel.getCenter(player).x-10, playerModel.getCenter(player).y);
			else newBullet.setLocation(playerModel.getCenter(player).x+10, playerModel.getCenter(player).y);
		}

		for (int i=0; i<2; ++i)
		{
			Bullet newBullet = new Bullet();
			newBullet.setPowerOfBullet(power);
			newBullet.setImageOfBullet("/BulletBlaster.png");
			listOfPlayerBullets.add(newBullet);
			newBullet.setDeltasOfBullet(deltaX, deltaY);
			if (i == 0) newBullet.setLocation(playerModel.getCenter(player).x-5, playerModel.getCenter(player).y-5);
			else newBullet.setLocation(playerModel.getCenter(player).x-5, playerModel.getCenter(player).y+5);
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
			int stepX = (int)(8 *bulletToMove.deltaX);
			int stepY = (int)(8*bulletToMove.deltaY);
			bulletToMove.setLocation(bulletToMove.getLocation().x+stepX, bulletToMove.getLocation().y-8);
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
					updateEnemyLife(enemyRectangle, bulletRectangle.getPowerOfBullet());
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
