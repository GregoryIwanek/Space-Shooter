package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BulletsModel 
{
	
	public BulletsModel() {}
	
	//sets size of an bullet object
	public void setBulletSize(Bullet bullet, Dimension size)
	{
		bullet.setSize(size);
	}
	
	public void calculateMovementAsBlaster()
	{
		
	}
	
	//calculating movement factors for missile weapons ( follows target)
	public void calculateMovementAsAMissile(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{
		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
		 double deltaX = Math.sin(angle);
		 double deltaY = Math.cos(angle);
	}
	
	public void calculateMovementAsLaser()
	{
		
	}
	
	//sets location of a given bullet object 
	public void setLocationOfBullet(Bullet bullet, int x, int y)
	{
		bullet.setLocation(x,y);
	}
	
	//sets power of a given bullet object (damage)
	public void setPowerOfBullet(Bullet bullet, int power)
	{
		bullet.setPowerOfBullet(power);
	}
	
	//calculate direction/step on X and Y axis for a bullet
	public void calculateDeltasOfBullet(Bullet bullet, int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{
		//calculate angle
		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
		//sets deltas for steps on X and Y directions
		bullet.setDeltasOfBullet(Math.sin(angle), Math.cos(angle));
	}
	
	//sets fixed deltas for bullet ( exm. players blaster bullets)
	public void setDeltasOfBullet(Bullet bullet, double deltaX, double deltaY)
	{
		bullet.setDeltasOfBullet(deltaX, deltaY);
	}
	
	//sets image of an bullet object to paint
	public void setImageOfBullet(Bullet bullet, String path)
	{
		//initiation of a image variable
		BufferedImage image = null;
		try {
			//sets image by a given picture path
		 image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		//assigns image to an bullet object
		bullet.setImage(image);
	}
	
	//gets power of a bullet (damage)
	public int getPowerOfBullet(Bullet bullet)
	{
		return bullet.getPowerOfBullet();
	}
	
	//gets factor for step on X axis
	public double getDeltaXOfBullet(Bullet bullet)
	{
		return bullet.getDeltaXOfBullet();
	}
	
	//gets factor for step on Y axis
	public double getDeltaYOfBullet(Bullet bullet)
	{
		return bullet.getDeltaYBullet();
	}
	
	//gets current global location of bullet
	public Point getLocationOfBullet(Bullet bullet)
	{
		return new Point(bullet.getLocation());
	}
}
