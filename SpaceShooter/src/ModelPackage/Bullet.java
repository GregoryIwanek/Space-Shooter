package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Rectangle
{
	public double deltaX = 0;
	public double deltaY = 0;
	private int power = 0;
	private BufferedImage imageOfBullet;
	
	public Bullet() 
	{
		setSize(new Dimension(6,6));
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
	public void setImageOfBullet(String path)
	{
		try {
			imageOfBullet = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
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
	
}
