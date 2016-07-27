package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Rectangle
{
	private double deltaX = 0;
	private double deltaY = 0;
	private int power = 0;
	private BufferedImage imageOfBullet;
	
	public Bullet() 
	{
		
	}
	public void setPowerOfBullet(int power)
	{
		this.power = power;
	}
	public void setDeltasOfBullet(double deltaX, double deltaY)
	{
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	public void setImage(BufferedImage imageOfBullet)
	{
		this.imageOfBullet = imageOfBullet;
	}
	//GETTERS
	//-------------------------------------------------------------------------------------------------
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}
	public BufferedImage getImageOfBullet()
	{
		return imageOfBullet;
	}
	public int getPowerOfBullet()
	{
		return power;
	}
	public double getDeltaXOfBullet()
	{
		return deltaX;
	}
	public double getDeltaYBullet()
	{
		return deltaY;
	}
	
}
