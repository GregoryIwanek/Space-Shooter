package ModelPackage;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;

public class GameModel 
{
	static private SceneModel sceneModel;
	static private PlayerModel playerModel;
	static private EnemyModel enemyModel;
	private BulletsModel bulletsModel;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;

	public GameModel()
	{
		sceneModel = new SceneModel();
		playerModel = new PlayerModel(500,500);
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
	public void checkIfEnemyInCollision(PlayerView player)
	{
		ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
		while (enemyIterator.hasNext())
		{
			Rectangle enemyRectangle = enemyIterator.next().getBounds();
			if (player.intersects(enemyRectangle))
			{
				enemyIterator.remove();
				playerModel.setShieldToDisplay(-25);
				playerModel.setLifeToDisplay(-25);
			}
		}
	}

	public void setNewBullets()
	{
		for (Enemy enemy : listOfEnemyShips)
		{
			Bullet newBullet = new Bullet();
			newBullet.setImageOfBullet("/Bullet.png");
			listOfBullets.add(newBullet);
			bulletsModel.calculateMovementOfBullet(enemy.getCenter().x, enemy.getCenter().y,
					playerModel.getCenter().x, playerModel.getCenter().y );
			newBullet.setDeltasOfBullet(bulletsModel.deltaX, bulletsModel.deltaY);
			newBullet.setLocation(enemy.getCenter().x, enemy.getCenter().y);
			newBullet.setOnwer("ENEMY");
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
	public void checkIfBulletInCollision(PlayerView player)
	{
		ListIterator<Bullet> bulletIterator = listOfBullets.listIterator();
		while ( bulletIterator.hasNext())
		{
			Rectangle bulletRectangle =  bulletIterator.next().getBounds();
			if (player.intersects(bulletRectangle))
			{
				bulletIterator.remove();
				playerModel.setShieldToDisplay(-5);
				playerModel.setLifeToDisplay(-5);
			}
		}
	}
	public void setNewBulletsOfPlayer()
	{
		Bullet newBullet = new Bullet();
		newBullet.setImageOfBullet("/BulletBlaster.png");
		listOfPlayerBullets.add(newBullet);
		newBullet.setDeltasOfBullet(0, 1);
		newBullet.setLocation(playerModel.getCenter().x, playerModel.getCenter().y);
		newBullet.setOnwer("PLAYER");
	}
	public void updatePositionOfPlayerBullets()
	{
		for (Bullet bulletToMove : listOfPlayerBullets)
		{
			bulletToMove.setLocation(bulletToMove.getLocation().x, bulletToMove.getLocation().y-5);
		}
	}
	public void checkIfPlayerBulletInCollision()
	{
		ListIterator<Bullet> bulletIterator = listOfPlayerBullets.listIterator();
		while ( bulletIterator.hasNext())
		{
			Rectangle bulletRectangle =  bulletIterator.next().getBounds();
			ListIterator<Enemy> enemyIterator = listOfEnemyShips.listIterator();
			while(enemyIterator.hasNext())
			{
				Rectangle enemyRectangle = enemyIterator.next().getBounds();
				if (bulletRectangle.intersects(enemyRectangle))
				{
					bulletIterator.remove();
					enemyIterator.remove();
					playerModel.setShieldToDisplay(5);
				}
			}
		}
	}


	public void setSizeToModel(int width, int height)
	{
		playerModel.setSize(width, height);
	}
	//GETTERS
	//--------------------------------------------------------------------------------------------------------
	public SceneModel getSceneModel()
	{
		return sceneModel;
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
