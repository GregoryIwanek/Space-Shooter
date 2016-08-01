package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class EnemyModel 
{
	//random generator used in setting starting position of enemy object
	Random randomGenerator;

	public EnemyModel() 
	{
		randomGenerator = new Random();
	}

	//gets random position of enemy ( used as X axis)
	public int getRandomStartingPos()
	{
		return randomGenerator.nextInt(600);
	}

	//sets size of an enemy object
	public void setEnemySize(Enemy enemy, Dimension size)
	{
		enemy.setSize(size);
	}

	//sets life of an object
	public void setEnemyLife(Enemy enemy, int points)
	{
		enemy.setLife(points);
	}
	
	//updates enemy life after hit by bullet
	public void updateEnemyLife(Enemy enemy, int points)
	{
		enemy.setLife(enemy.getEnemyLife() - points);
	}
	
	public void setIfIsDestroyed(Enemy enemy)
	{
		if (enemy.getEnemyLife() <= 0)
		{
			enemy.setIfIsDestroyed(true);
		}
	}
	
	public boolean getIsDestroyed(Enemy enemy)
	{
		return enemy.getIsDestroyed();
	}
	
	//sets if enemy object is asteroid
	public void setEnemyIfAsteroid(Enemy enemy, boolean isAsteroid)
	{
		enemy.setIfAsteroid(isAsteroid);
	}

	//sets speed of an enemy
	public void setEnemySpeed(Enemy enemy, int speed, boolean isAsteroid)
	{
		//if speed 
		if (isAsteroid == true)
		{
			//for fast enemies, not depending on lvl of game
			enemy.setSpeed(speed);
		}
		else 
		{
			//put limit on max speed, speed depends on lvl of a game
			if (speed > 2)
			{
				enemy.setSpeed(2);
			}
			else enemy.setSpeed(speed);
		}
	}

	//sets image of an enemy object to paint
	public void setImage(Enemy enemy, String path)
	{
		//initiation of image variable
		BufferedImage image = null;

		try {
			//sets image by given path
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}

		//sets image to an object
		enemy.setImage(image);
	}

	//set new position of enemy object
	public void setNewPosition(Enemy enemy, int x, int y)
	{
		enemy.setLocation(x, y);
	}

	//gets center of enemy object in global coordinate system
	public Point getCenter(Enemy enemy)
	{
		return enemy.getCenter();
	}

	//gets current position of an enemy in global coordinate system
	public Point getPosition(Enemy enemy)
	{
		return enemy.getLocation();
	}

	//gets speed of given enemy object
	public int getSpeed(Enemy enemy)
	{
		return enemy.getSpeed();
	}
	
	//gets if enemy object is an asteroid
	public boolean getIfEnemyAsteroid(Enemy enemy)
	{
		return enemy.getIfAsteroid();
	}
}
