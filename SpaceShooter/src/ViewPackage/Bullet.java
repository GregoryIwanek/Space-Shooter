package ViewPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Rectangle
{
	int xCurrentPosition = 0;
	int yCurrentPosition = 0;
	public double deltaX = 0;
	public double deltaY = 0;
	private BufferedImage imageOfBullet;
	String owner;
	
	public Bullet() {
		setSize(new Dimension(8,8));
	}
	public void setOnwer(String owner)
	{
		this.owner = owner;
	}
	public String getOwner()
	{
		return owner;
	}
	public void setPosition(int x, int y)
	{
		//xCurrentPosition = x;
		//yCurrentPosition = y;
	}
	public void setPosition(double x, double y)
	{
		//xCurrentPosition = (int) x;
		//yCurrentPosition = (int) y;
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
	public BufferedImage getImageOfBullet()
	{
		return imageOfBullet;
	}

	//GETTERS
	//-------------------------------------------------------------------------------------------------
	public Point getPosition()
	{
		return new Point(this.getLocation());
	}
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
		//return new Point (xCurrentPosition + this.getBounds().width/2, yCurrentPosition + this.getBounds().height/2);
	}
	
}
