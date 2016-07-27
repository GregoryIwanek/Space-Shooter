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
	
	//sets image of an enemy object to paint
	public void setImageOfEnemy(Enemy enemy, String path)
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
	
//	//
//	public void calculateMovementOfEnemy(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
//	{		
////		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
////		 deltaX = Math.sin(angle);
////		 deltaY = Math.cos(angle);
//	}
	
	//set new position of enemy object
	public void setNewPosition(Enemy enemy, int x, int y)
	{
		enemy.setLocation(x, y);
	}
	
	//gets center of enemy object in global coordinate system
	public Point getCenterOfEnemy(Enemy enemy)
	{
		return enemy.getCenter();
	}
	
	//gets current position of an enemy in global coordinate system
	public Point getPositionOfEnemy(Enemy enemy)
	{
		return enemy.getLocation();
	}
}
