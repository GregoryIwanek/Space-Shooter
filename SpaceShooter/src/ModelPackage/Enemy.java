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
	private BufferedImage imageOfEnemy;
	private ArrayList<Bullet> listOfBullets;

	public Enemy() 
	{
		listOfBullets = new ArrayList<Bullet>();
		this.setSize(new Dimension(35, 50));
		setImageOfEnemy();
	}

	public void setPosition(int x, int y)
	{
		//this.x = x;
		//this.y = y;
	}

	public void setPosition(double x, double y)
	{
		//this.x = (int) x;
		//this.y = (int) y;
	}

	public Point getPosition()
	{
		return new Point(this.getLocation());
	}
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
		//return new Point(x+this.getBounds().width/2, y + this.getBounds().height/2);
	}

	public void setNewBullet()
	{

	}

	public ArrayList<Bullet> getListOfBullets()
	{
		return listOfBullets;
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
