package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy extends Rectangle
{
	private int hp = 1000;
	private BufferedImage imageOfEnemy;

	public Enemy() 
	{

	}
	public void updateLife(int points)
	{
		hp -= points;
	}
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

	public void setImage(BufferedImage imageOfEnemy)
	{
		this.imageOfEnemy = imageOfEnemy;
	}
	public BufferedImage getImageOfEnemy()
	{
		return imageOfEnemy;
	}
}
