package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class EnemyModel 
{
	Random randomGenerator;

	public EnemyModel() 
	{
		randomGenerator = new Random();
	}
	
	public int getRandomStartingPos()
	{
		return randomGenerator.nextInt(600);
	}
	
	public void setEnemySize(Enemy enemy, Dimension size)
	{
		enemy.setSize(size);
	}
	public void setImageOfEnemy(Enemy enemy, String path)
	{
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		enemy.setImage(image);
	}
	
	public void calculateMovementOfEnemy(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{		
//		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
//		 deltaX = Math.sin(angle);
//		 deltaY = Math.cos(angle);
	}
	
	public void setNewPosition(Enemy enemy, int x, int y)
	{
		enemy.setLocation(x, y);
	}
	public Enemy getNewEnemyShip()
	{
		return new Enemy();
	}
	
	public Point getCenterOfEnemy(Enemy enemy)
	{
		return enemy.getCenter();
	}
}
