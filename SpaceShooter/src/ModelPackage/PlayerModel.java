package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PlayerModel 
{
	public PlayerModel(){}

	//calculate current shield power and amount of life of a player
	public void setShieldAndLife(Player player, int shieldPoints, int hpPoints)
	{
		int currentShield = player.getShield();
		int currentLife = player.getLife();

		/*calculate only if current shield is in range 0-maxPlayerShield AND player hit by ENEMY/BULLET object
		 *hpPoints different than 0 and given to this method only when called from collision with BONUS object*/
		if (currentShield <= player.getMaxShield() && hpPoints == 0)
		{
			//add given points to shield ( can add and subtract value)
			currentShield += shieldPoints;

			//if result would be below zero, subtract life of player and set shield as minimum (0)
			if (currentShield < 0)
			{
				//subtract value of life if calculated shield is below 0
				currentLife += currentShield;

				//make sure life stays more than 0
				if (currentLife < 0) currentLife = 0;

				//reset shield to 0, that's minimum, possible value of a shield
				currentShield = 0;
			}
			//make sure shield stays below max possible value
			else if (currentShield > player.getMaxShield())
			{
				currentShield = player.getMaxShield();
			}
		}

		//add given BONUS of hpPoints
		currentLife += hpPoints;

		//set calculated value to a player object
		player.setShield(currentShield);
		player.setLife(currentLife);
	}

	/*calculates movement of player object, 
	 * based on given list of pressed keys ( list holds max. 2keys pressed at same time)*/
	public void calculateMovement(Player player, ArrayList<Integer> listOfPressedKeys)
	{
		//if list contains LEFT BUTTON 
		if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(37) == true)
		{
			updateX(player, false);
			//and if list contains UP/DOWN buttons, move LEFT UP/DOWN
			if (listOfPressedKeys.contains(38) == true) updateY(player, false);
			else if (listOfPressedKeys.contains(40) == true) updateY(player, true);
		}
		//if list contains UP BUTTON
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(38) == true)
		{
			updateY(player, false);
			//and if list contains LEFT/RIGHT button move UP LEFT/RIGHT
			if (listOfPressedKeys.contains(37) == true) updateX(player, false);
			else if (listOfPressedKeys.contains(39) == true) updateX(player, true);
		}
		//if list contains RIGHT button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(39) == true)
		{
			updateX(player, true);
			//and if list contains UP/DOWN button move RIGHT UP/DOWN
			if (listOfPressedKeys.contains(38) == true) updateY(player, false);
			else if (listOfPressedKeys.contains(40) == true) updateY(player, true);
		}
		//if list contains DOWN button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(40) == true)
		{
			updateY(player, true);
			//and if list contains LEFT/RIGHT button move DOWN LEFT/RIGHT
			if (listOfPressedKeys.contains(37) == true) updateX(player, false);
			else if (listOfPressedKeys.contains(39) == true) updateX(player, true);
		}
	}

	//update X position of given player object
	public void updateX(Player player, boolean isIncreasing)
	{
		//increase/decrease X value only if player is inside border of scene
		if (isIncreasing == true && player.getLocation().x < 585)
		{
			player.setLocation(player.getLocation().x+5, player.getLocation().y);
		}
		else if (isIncreasing == false && player.getLocation().x > 0)
		{
			player.setLocation(player.getLocation().x-5, player.getLocation().y);
		}
	}

	//update Y position of given player object
	public void updateY(Player player, boolean isIncreasing)
	{
		//increase/decrease Y value only if player is inside border of scene
		if (isIncreasing == true && player.getLocation().y  < 585)
		{
			player.setLocation(player.getLocation().x, player.getLocation().y+5);
		}
		else if (isIncreasing == false && player.getLocation().y > 0)
		{
			player.setLocation(player.getLocation().x, player.getLocation().y-5);
		}
	}

	public void setIfMaxNumber(Player player)
	{
		//checks if all weapons have max number of bullets
		if (player.getWeaponInfo("numberOfBlaster") >= 7 && player.getWeaponInfo("numberOfMissiles") >= 4 
				&& player.getWeaponInfo("numberOfLaser") >= 3)
		{
			player.setIfMaxNumber(true);
		}
	}

	public void setIfMaxSpeed(Player player)
	{
		if (player.getWeaponInfo("speedBlaster") >= 13 && player.getWeaponInfo("speedMissile") >= 9)
		{
			player.setIfMaxSpeed(true);
		}
	}

	//sets type of weapon used by player
	public void setTypeOfWeapon(Player player, String typeOfWeapon)
	{
		player.setTypeOfWeapon(typeOfWeapon);
	}

	//sets new maximum shield value
	public void setMaxShield(Player player, int points)
	{
		int newShield = player.getMaxShield();
		player.setMaxShield(newShield + points);
	}

	public void setNumberOfBulletsBlaster(Player player, int number)
	{
		if (player.getWeaponInfo("numberOfBlaster") < 7)
		{
			player.setNumberOfBulletsBlaster(player.getWeaponInfo("numberOfBlaster")+number);
		}
	}

	public void setNumberOfBulletsMissiles(Player player, int number)
	{
		if (player.getWeaponInfo("numberOfMissiles") < 4)
		{
			player.setNumberOfBulletsMissiles(player.getWeaponInfo("numberOfMissiles")+number);	
		}
	}

	public void setNumberOfBulletsLaser(Player player, int number)
	{
		if (player.getWeaponInfo("numberOfLaser") < 3)
		{
			player.setNumberOfBulletsLaser(player.getWeaponInfo("numberOfLaser")+number);
		}
	}

	public void setNumberOfBombs(Player player, int number)
	{
		if (player.getWeaponInfo("numberOfBombs") < 4)
		{
			player.setNumberOfBombs(player.getWeaponInfo("numberOfBombs")+number);
		}
	}

	public void setPowerBlaster(Player player, int power)
	{
		player.setPowerBlaster(player.getWeaponInfo("powerBlaster")+power);
	}

	public void setPowerMissiles(Player player, int power)
	{
		player.setPowerMissiles(player.getWeaponInfo("powerMissile")+power);
	}

	public void setPowerLaser(Player player, int power)
	{
		player.setPowerLaser(player.getWeaponInfo("powerLaser")+power);
	}

	public void setSpeedBlaster(Player player, int speed)
	{
		//max blaster speed 13
		if (player.getWeaponInfo("speedBlaster") < 13)
		{
			player.setSpeedBlaster(player.getWeaponInfo("speedBlaster")+speed);
		}

	}

	public void setSpeedMissile(Player player, int speed)
	{
		//max missile speed 9
		if (player.getWeaponInfo("speedMissile") < 9)
		{
			player.setSpeedMissile(player.getWeaponInfo("speedMissile")+speed);
		}
	}

	//series of possible bonuses, upgrade players stats
	public void setBonusUpgrade(Bonus bonus, Player player)
	{		
		String typeOfBonus = bonus.getType();
		int valueOfBonus = bonus.getInformationFromMap(typeOfBonus);

		switch (typeOfBonus) {
		case "hpRestored":
			setShieldAndLife(player, 0, valueOfBonus);
			break;
		case "shieldRestored":
			setShieldAndLife(player, valueOfBonus, 0);
			break;
		case "shieldPernament":
			//increase max value of a shield
			setMaxShield(player, valueOfBonus);
			break;
		case "extraBulletBlaster":
			//adds extra blaster bullet
			setNumberOfBulletsBlaster(player, valueOfBonus);
			setIfMaxNumber(player);
			break;
		case "extraBulletLaser":
			//adds extra laser beam
			setNumberOfBulletsLaser(player, valueOfBonus);
			setIfMaxNumber(player);
			break;
		case "extraBulletMissile":
			//adds extra missile bullet
			setNumberOfBulletsMissiles(player, valueOfBonus);
			setIfMaxNumber(player);
			break;
		case "extraBlasterPower":
			//increase power of blaster bullets
			setPowerBlaster(player, valueOfBonus);
			break;
		case "extraLaserPower":
			//increase power of laser beam
			setPowerLaser(player, valueOfBonus);
			break;
		case "extraMissilePower":
			//increase power of missile bullets
			setPowerMissiles(player, valueOfBonus);
			break;
		case "extraBlasterSpeed":
			//increase speed of blaster bullets
			setSpeedBlaster(player, valueOfBonus);
			setIfMaxSpeed(player);
			break;
		case "extraMissileSpeed":
			//increase speed of missile bullets
			setSpeedMissile(player, valueOfBonus);
			setIfMaxSpeed(player);
			break;
		default:
			break;
		}
	}

	//sets new amount of points ( shoot down enemies) of a player
	public void updatePoints(Player player, int points)
	{
		//sets points as current points + given points
		player.setPoints(player.getPoints()+ points);
	}

	//sets image for a given player object
	public void setImage(Player player, String path)
	{
		//initiation of image variable
		BufferedImage image = null;

		try {
			//sets image by using String path
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		//sets image to a player object
		player.setImage(image);
	}

	//sets size of player object
	public void setSize(Player player, Dimension size)
	{
		player.setSize(size);
	}

	//gets position in global coordinates system of a player object
	public Point getNewPosition(Player player)
	{
		return player.getLocation();
	}

	//gets center of player in global coordinate system
	public Point getCenter(Player player)
	{
		return player.getCenter();
	}

	//gets power of shield of player
	public int getPlayersShield(Player player)
	{
		return player.getShield();
	}

	//gets life amount of player
	public int getPlayersLife(Player player)
	{
		return player.getLife();
	}

	//gets points of player
	public int getPlayersPoints(Player player)
	{
		return player.getPoints();
	}

	//gets current used type of weapon
	public String getTypeOfWeapon(Player player)
	{
		return player.getTypeOfWeapon();
	}

	public int getWeaponInfo(Player player, String info)
	{
		return player.getWeaponInfo(info);
	}

	public int getMaxShield(Player player)
	{
		return player.getMaxShield();
	}
}
