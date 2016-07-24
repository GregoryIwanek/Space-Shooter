package ModelPackage;

import java.awt.Point;
import java.math.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class EnemyModel 
{
	private int xCurrentPosition;
	private int yCurrentPosition;
	double deltaX=0, deltaY=0;
	private int hp;
	Random randomGenerator;
	
	
	public EnemyModel(int xCurrentPosition, int yCurrentPosition) 
	{
		this.xCurrentPosition = xCurrentPosition;
		this.yCurrentPosition = yCurrentPosition;
		randomGenerator = new Random();
		hp = 1;
	}
	
	public int getRandomStartingPos()
	{
		return randomGenerator.nextInt(580);
	}
	
	public void calculateMovementOfEnemy(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{		
//		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
//		 deltaX = Math.sin(angle);
//		 deltaY = Math.cos(angle);
	}
	public void updateX()
	{
		
	}
	public void updateY()
	{
		yCurrentPosition += 5;
	}
	
	public void setNewPosition(int x, int y, Enemy enemy)
	{
		enemy.setLocation(x, y);
	}
	public Enemy getNewEnemyShip()
	{
		return new Enemy();
	}
	public Point getNewPosition()
	{
		return new Point(xCurrentPosition, yCurrentPosition);
	}
}
