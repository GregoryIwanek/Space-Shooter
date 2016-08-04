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
	
	//(ONLY FOR LASER) sets current lifetime of bullet
	public void setTime(Bullet bullet, int time)
	{
		int currentTime = bullet.getTime();
		bullet.setTime(currentTime+time);
	}
	
	//sets speed of bullet ( missiles and blaster bullets have different speed)
	public void setSpeed(Bullet bullet, int speed)
	{
		bullet.setSpeed(speed);
	}
	
	//(ONLY FOR LASER) sets true/false depending if laser collides with enemy
	public void setIsInCollision(Bullet bullet, boolean isInCollision)
	{
		bullet.setIsInCollision(isInCollision);
	}

	//sets target for missile kind of bullet to follow
	public void setTargetForMissile(Bullet bullet, Enemy target)
	{
		bullet.setTargetForMissile(target);
	}

	//defines type of bullet 
	public void setType(Bullet bullet, String type)
	{
		bullet.setType(type);
	}

	//calculating movement factors for missile weapons ( follows target)
	public void calculateMovementAsAMissile( Bullet bullet, Enemy enemyTarget)
	{
		//update deltas for missile ONLY if there are enemies on a scene
		if (enemyTarget != null)
		{
			double angle = Math.atan2(enemyTarget.getCenterX() - bullet.getCenterX(), enemyTarget.getCenterY() - bullet.getCenterY());
			double deltaX = Math.sin(angle);
			double deltaY = Math.cos(angle);
			
			bullet.setDeltas(deltaX, deltaY);
		}
	}
	
	//(ONLY FOR LASER) store location of bullet to Player ( example-> moved 10 on X axis from center of Player)
	public void setLocationAsLaser(Bullet bullet, Point location)
	{
		bullet.setLocationAsLaser(location);
	}

	//sets location of a given bullet object 
	public void setLocation(Bullet bullet, int x, int y)
	{
		bullet.setLocation(x,y);
	}

	//sets power of a given bullet object (damage)
	public void setPower(Bullet bullet, int power)
	{
		bullet.setPower(power);
	}

	//calculate direction/step on X and Y axis for a bullet
	public void calculateDeltas(Bullet bullet, int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{
		//calculate angle
		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
		//sets deltas for steps on X and Y directions
		bullet.setDeltas(Math.sin(angle), Math.cos(angle));
	}

	//sets fixed deltas for bullet ( exm. players blaster bullets)
	public void setDeltas(Bullet bullet, double deltaX, double deltaY)
	{
		bullet.setDeltas(deltaX, deltaY);
	}

	//sets image of an bullet object to paint
	public void setImage(Bullet bullet, String path)
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
	public int getPower(Bullet bullet)
	{
		return bullet.getPower();
	}

	//gets factor for step on X axis
	public double getDeltaX(Bullet bullet)
	{
		return bullet.getDeltaX();
	}

	//gets factor for step on Y axis
	public double getDeltaY(Bullet bullet)
	{
		return bullet.getDeltaY();
	}

	//gets current global location of bullet
	public Point getLocation(Bullet bullet)
	{
		return new Point(bullet.getLocation());
	}

	//gets type of bullet (blaster, missile, laser)
	public String getType(Bullet bullet)
	{
		return bullet.getType();
	}

	//gets target of missile kind of bullet
	public Enemy getTargetOfMissile(Bullet bullet)
	{
		return bullet.getTargetOfMissile();
	}
	
	//gets speed of given bullet ( used to calculate step X Y)
	public int getSpeed(Bullet bullet)
	{
		return bullet.getSpeed();
	}
	
	//gets size of bullet
	public Dimension getSize(Bullet bullet)
	{
		return bullet.getSize();
	}
	
	//(ONLY FOR LASER BULLET) gets location as laser (moved from starting location by height)
	public Point getLocationAsLaser(Bullet bullet)
	{
		return bullet.getLocationAsLaser();
	}
	
	//(ONLY FOR LASER) gets if laser is in collision
	public boolean getIsInCollision(Bullet bullet)
	{
		return bullet.getIsInCollision();
	}
	
	//(ONLY FOR LASER) gets lifetime of laser bullet
	public int getTime(Bullet bullet)
	{
		return bullet.getTime();
	}
}
