package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Rectangle
{
	private int x;
	private int y;
	private int hp = 1000;
	private BufferedImage imageOfEnemy;

	public Enemy() 
	{
		this.setSize(new Dimension(35, 50));
		setImageOfEnemy();
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

	public void setImageOfEnemy()
	{
		try {
			imageOfEnemy = ImageIO.read(getClass().getResourceAsStream("/Enemy_ship_1.png"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public BufferedImage getImageOfEnemy()
	{
		return imageOfEnemy;
	}
}
