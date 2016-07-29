package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends Rectangle
{
	private double deltaX = 0; //factor for step on X axis
	private double deltaY = 0; //factor for step on Y axis
	private int power = 0; //power of bullet (damage)
	private int speed = 8; //different speed for missiles and blaster, by default speed of blaster
	private Enemy target; //for missile kind of bullet-> target to follow
	private String typeOfBullet = "BLASTER";
	private BufferedImage imageOfBullet;
	
	public Bullet(){}
	
	//sets power of a bullet (damage)
	public void setPowerOfBullet(int power)
	{
		this.power = power;
	}
	
	//gets speed of bullet ( different speed for missiles and blaster)
	public void setSpeedOfBullet(int speed)
	{
		this.speed = speed;
	}
	
	//sets information about type of bullet
	public void setTypeOfBullet(String typeOfBullet)
	{
		this.typeOfBullet = typeOfBullet;
	}
	
	//sets target to follow for missile kind of bullet
	public void setTargetForMissile(Enemy target)
	{
		this.target = target;
	}
	
	//sets step factor of a bullet ( used for calculating step on X and Y axis on scene)
	public void setDeltasOfBullet(double deltaX, double deltaY)
	{
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	//sets image of a bullet to paint on scene
	public void setImage(BufferedImage imageOfBullet)
	{
		this.imageOfBullet = imageOfBullet;
	}
	
	//GETTERS
	//-------------------------------------------------------------------------------------------------
	
	//gets center of a bullet in a global coordinate system
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}
	
	//gets image of a bullet to paint
	public BufferedImage getImageOfBullet()
	{
		return imageOfBullet;
	}
	
	//gets power (damage) of a bullet
	public int getPowerOfBullet()
	{
		return power;
	}
	
	//gets factor for step on X axis
	public double getDeltaXOfBullet()
	{
		return deltaX;
	}
	
	//gets factor for step on Y axis
	public double getDeltaYBullet()
	{
		return deltaY;
	}
	
	//gets information about type of bullet
	public String getTypeOfBullet()
	{
		return typeOfBullet;
	}
	
	//gets target of missile
	public Enemy getTargetOfMissile()
	{
		return target;
	}
	
	//gets speed of bullet ( different speed for missiles and blaster)
	public int getSpeedOfBullet()
	{
		return speed;
	}
}
