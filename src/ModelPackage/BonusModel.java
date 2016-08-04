package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class BonusModel
{
	static private Random randomGenerator;

	public BonusModel()
	{
		randomGenerator = new Random();
	}

	//sets size of an bonus object
	public void setBonusSize(Bonus bonus, Dimension size)
	{
		bonus.setSize(size);
	}

	//gets random position of Bonus ( used on X axis)
	public int getRandomStartingPos()
	{
		return randomGenerator.nextInt(600);
	}

	//set new position of Bonus object
	public void setNewPosition(Bonus bonus, int x, int y)
	{
		bonus.setLocation(x, y);
	}

	//roll which type of bonus will be spawn
	public void rollNewType(Bonus bonus, Player player)
	{
		//to determine if new type was chosen or not
		boolean isChosen = false;

		do {
			int count = randomGenerator.nextInt(4);

			switch (count) 
			{
			case 0:
				rollTypeOfLifeBonus(bonus);
				isChosen = true;
				break;
			case 1:
				rollTypeOfPowerBonus(bonus);
				isChosen = true;
				break;
			case 2:
				//roll only if statistic max speed of bullets wasn't reached
				if (player.getIfIsMaxSpeed() == false)
				{
					rollTypeOfSpeedBonus(bonus);
					isChosen = true;
				}
				break;
			case 3:
				//roll only if weapons can have bigger number of bullets
				if (player.getIfIsMaxNumber() == false)
				{
					rollTypeOfNumberBonus(bonus, false);
					isChosen = true;
				}
				else 
				{	//if reached, roll bomb bonus instead
					rollTypeOfNumberBonus(bonus, true);
					isChosen = true;
				}
				break;
			default:
				rollTypeOfLifeBonus(bonus);
				break;
			}	
		}
		while (isChosen != true);
	}

	//sets random weapon damage upgrade
	private void rollTypeOfPowerBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(3);

		switch (type) 
		{
		case 0:
			//extra blaster power
			setType(bonus, "extraBlasterPower");
			break;
		case 1:
			//extra laser power
			setType(bonus, "extraLaserPower");
			break;
		case 2:
			//extra missile power
			setType(bonus, "extraMissilePower");
			break;
		default: setType(bonus, "extraBlasterPower");
		break;
		}
	}

	//sets random bullets number upgrade
	private void rollTypeOfNumberBonus(Bonus bonus, boolean isMaxNumberBullets)
	{
		if (isMaxNumberBullets == false)
		{
			int type = randomGenerator.nextInt(3);
			switch (type) 
			{
			case 0:
				//add extra blaster bullet
				setType(bonus, "extraBulletBlaster");
				break;
			case 1:
				//add extra missile bullet
				setType(bonus, "extraBulletMissile");
				break;
			case 2:
				//add extra laser beam
				setType(bonus, "extraBulletLaser");
				break;
			default:
				break;
			}
		}
		else setType(bonus, "extraBomb");
	}

	//sets random life/shield upgrade
	private void rollTypeOfLifeBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(3);

		switch (type) 
		{
		case 0:
			//restore number of hp
			setType(bonus, "hpRestored");
			break;
		case 1:
			//restore number of shield
			setType(bonus, "shieldRestored");
			break;
		case 2:
			//increase maximum shield value
			setType(bonus, "shieldPernament");
			break;
		default:
			break;
		}
	}

	//sets random bullets speed bonus
	private void rollTypeOfSpeedBonus(Bonus bonus)
	{
		int type = randomGenerator.nextInt(2);

		switch (type) 
		{
		case 0:
			//increase blaster bullet speed
			setType(bonus, "extraBlasterSpeed");
			break;
		case 1:
			//increase missile bullet speed
			setType(bonus, "extraMissileSpeed");
			break;
		default:
			break;
		}
	}
	
	//assign rolled type of bonus to the object
	public void setType(Bonus bonus, String type)
	{
		bonus.setType(type);
	}

	//gets value of upgrade from given bonus object
	public int getValue(Bonus bonus)
	{
		return bonus.getInformationFromMap(bonus.getType());
	}

	public void setImage(Bonus bonus)
	{
		/*manipulate names of variables to get correct path of image ( example: /bonusShieldRestored instead of /bonusshieldRestored);
		 names of images are set to fit name of types of bonuses */
		String path = "/" + bonus.getType().substring(0, 1).toUpperCase() 
				+ bonus.getType().substring(1, bonus.getType().length()) + ".png";

		//assign correct, fixed image, to a bonus object
		setImageToObject(bonus, path);
	}

	//sets image of an object to paint on scene
	public void setImageToObject(Bonus bonus, String path)
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
	public Point getPosition(Bonus bonus)
	{
		return bonus.getLocation();
	}
}
