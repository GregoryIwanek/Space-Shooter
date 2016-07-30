package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BonusModel
{
	private Random randomGenerator;
	
	public BonusModel()
	{
		randomGenerator = new Random();
	}
	
	//sets size of an bonus object
	public void setBonusSize(Bonus bonus, Dimension size)
	{
		bonus.setSize(size);
	}
	
	//gets random position of Bonus ( used as X axis)
	public int getRandomStartingPos()
	{
		return randomGenerator.nextInt(600);
	}
	
	//set new position of enemy object
	public void setNewPosition(Bonus bonus, int x, int y)
	{
		bonus.setLocation(x, y);
	}
	
	//sets random type of weapon damage upgrade
	public void rollTypeOfPowerBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(3);
		
		switch (type) 
		{
		case 0:
			//extra blaster power
			setTypeOfBonus(bonus, "extraBlasterPower");
			break;
		case 1:
			//extra laser power
			setTypeOfBonus(bonus, "extraLaserPower");
			break;
		case 2:
			//extra missile power
			setTypeOfBonus(bonus, "extraMissilePower");
			break;
		default: setTypeOfBonus(bonus, "extraBlasterPower");
			break;
		}
	}
	
	//sets random type of bullets number upgrade
	public void rollTypeOfNumberBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(4);
		
		switch (type) 
		{
		case 0:
			//add extra blaster bullet
			setTypeOfBonus(bonus, "extraBulletBlaster");
			break;
		case 1:
			//add extra missile bullet
			setTypeOfBonus(bonus, "extraBulletMissile");
			break;
		case 2:
			//add extra laser beam
			setTypeOfBonus(bonus, "extraBulletLaser");
			break;
		case 3:
			//add extra bomb
			setTypeOfBonus(bonus, "extraBomb");
			break;
		default:
			break;
		}
	}
	
	//sets random type of life upgrade
	public void rollTypeOfLifeBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(3);
		
		switch (type) 
		{
		case 0:
			//restore number of hp
			setTypeOfBonus(bonus, "hpRestored");
			break;
		case 1:
			//restore number of shield
			setTypeOfBonus(bonus, "shieldRestored");
			break;
		case 2:
			//increase maximum shield value
			setTypeOfBonus(bonus, "shieldPernament");
			break;
		default:
			break;
		}
	}
	
	//sets random type of speed bonus
	public void rollTypeOfSpeedBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(2);
		
		switch (type) 
		{
		case 0:
			//increase blaster bullet speed
			setTypeOfBonus(bonus, "extraBlasterSpeed");
			break;
		case 1:
			//increase missile bullet speed
			setTypeOfBonus(bonus, "extraMissileSpeed");
			break;
		default:
			break;
		}
	}
	//assign rolled type of bonus to the object
	public void setTypeOfBonus(Bonus bonus, String typeOfBonus)
	{
		bonus.setTypeOfBonus(typeOfBonus);
	}
	
	//gets value of upgrade from given bonus object
	public int getValueOfBonus(Bonus bonus)
	{
		return bonus.getInformationFromMap(bonus.getTypeOfBonus());
	}
	
	public void setImageOfBonus(Bonus bonus)
	{
		/*manipulate names of variables to get correct path of image ( example: /bonusShieldRestored instead of /bonusshieldRestored);
		 names of images are set to fit name of types of bonuses */
		String path = "/" + bonus.getTypeOfBonus().substring(0, 1).toUpperCase() 
				+ bonus.getTypeOfBonus().substring(1, bonus.getTypeOfBonus().length()) + ".png";

		//assign correct, fixed image, to a bonus object
		setImageOfBonusToObject(bonus, path);
	}
	
	//sets image of an object to paint on scene
	public void setImageOfBonusToObject(Bonus bonus, String path)
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

		//assigns image to an bonus object
		bonus.setImage(image);
	}
	
	//gets position of bonus on a scene
	public Point getPositionOfBonus(Bonus bonus)
	{
		return bonus.getLocation();
	}
}
