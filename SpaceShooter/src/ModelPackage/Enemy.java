package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends Rectangle
{
	private int hp = 1000; //life of an enemy
	private BufferedImage imageOfEnemy;

	public Enemy(){}
	
	//sets new amount of life of an enemy object
	public void updateLife(int points)
	{
		hp -= points;
	}
	
	//sets information if enemy was destroyed and can be removed from scene
	public boolean isDestroyed()
	{
		if (hp<=0) return true;
		else return false;
	}
	
	//gets center of the enemy by global coordinates system, not local center
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}

	//sets image of an object to paint
	public void setImage(BufferedImage imageOfEnemy)
	{
		this.imageOfEnemy = imageOfEnemy;
	}
	
	//gets image of an object to paint
	public BufferedImage getImageOfEnemy()
	{
		return imageOfEnemy;
	}
}
