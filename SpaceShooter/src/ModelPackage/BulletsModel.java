package ModelPackage;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BulletsModel 
{
	double deltaX, deltaY;
	
	public BulletsModel() 
	{
		
	}
	
	public void calculateMovementAsBlaster()
	{
		
	}
	
	public void calculateMovementAsMisile()
	{
		
	}
	
	public void calculateMovementAsLaser()
	{
		
	}
	public void setLocationOfBullet(Bullet bullet, int x, int y)
	{
		bullet.setLocation(x,y);
	}
	public void setPowerOfBullet(Bullet bullet, int power)
	{
		bullet.setPowerOfBullet(power);
	}
	
	public void calculateMovementOfBullet(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{
		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
		 deltaX = Math.sin(angle);
		 deltaY = Math.cos(angle);
	}
	public void setDeltasOfBullet(Bullet bullet, double deltaX, double deltaY)
	{
		bullet.setDeltasOfBullet(deltaX, deltaY);
	}
	public void setImageOfBullet(Bullet bullet, String path)
	{
		BufferedImage image = null;
		try {
		 image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		bullet.setImageOfBullet(image);
	}
	public int getPowerOfBullet(Bullet bullet)
	{
		return bullet.getPowerOfBullet();
	}
	public double getDeltaXOfBullet(Bullet bullet)
	{
		return bullet.getDeltaXOfBullet();
	}
	public double getDeltaYOfBullet(Bullet bullet)
	{
		return bullet.getDeltaYBullet();
	}
	public Point getLocationOfBullet(Bullet bullet)
	{
		return new Point(bullet.getLocation());
	}
}
