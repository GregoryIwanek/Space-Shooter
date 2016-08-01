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
	private int time = 0; //(ONLY FOR LASER)-> to calculate lifetime of laser bullets ( laser bullets are alive for 1sec)
	private Enemy target; //for missile kind of bullet-> target to follow
	private Point locationAsLaser; //used for moving laser beam around with Player
	private String type = "BLASTER";
	private BufferedImage image;
	private boolean isInCollision =false;
	
	public Bullet(){}
	
	//sets power of a bullet (damage)
	public void setPower(int power)
	{
		this.power = power;
	}
	
	//sets speed of bullet ( different speed for missiles and blaster)
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public void setTime(int time)
	{
		this.time = time;
	}
	
	/*(ONLY FOR LASER) sets if bullet is in collision with enemy;
	 * used, to set if resize of bullet is needed or not*/
	public void setIsInCollision(boolean isInCollision)
	{
		this.isInCollision = isInCollision;
	}
	
	//(ONLY IF LASER) store location of laser beam to Player object ( example-> +10 on X axis to center of player etc)
	public void setLocationAsLaser(Point locationAsLaser)
	{
		this.locationAsLaser = locationAsLaser;
	}
	
	//sets information about type of bullet
	public void setType(String type)
	{
		this.type = type;
	}
	
	//sets target to follow for missile kind of bullet
	public void setTargetForMissile(Enemy target)
	{
		this.target = target;
	}
	
	//sets step factor of a bullet ( used for calculating step on X and Y axis on scene)
	public void setDeltas(double deltaX, double deltaY)
	{
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	//sets image of a bullet to paint on scene
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	//GETTERS
	//-------------------------------------------------------------------------------------------------
	
	//gets center of a bullet in a global coordinate system
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}
	
	//gets image of a bullet to paint
	public BufferedImage getImage()
	{
		return image;
	}
	
	//gets power (damage) of a bullet
	public int getPower()
	{
		return power;
	}
	
	//gets factor for step on X axis
	public double getDeltaX()
	{
		return deltaX;
	}
	
	//gets factor for step on Y axis
	public double getDeltaY()
	{
		return deltaY;
	}
	
	//gets information about type of bullet
	public String getType()
	{
		return type;
	}
	
	//gets target of missile
	public Enemy getTargetOfMissile()
	{
		return target;
	}
	
	//gets speed of bullet ( different speed for missiles and blaster)
	public int getSpeed()
	{
		return speed;
	}
	
	//returns location of Laser bullet to Player
	public Point getLocationAsLaser()
	{
		return locationAsLaser;
	}
	
	//(FOR LASER ONLY) gets if bullet is in collision with enemy
	public boolean getIsInCollision()
	{
		return isInCollision;
	}
	
	public int getTime()
	{
		return time;
	}
}
