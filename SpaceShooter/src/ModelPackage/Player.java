package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Rectangle
{
	//statistics of a player and his weapons
	static private int shield = 100, hp = 100; //default players shield and life
	static private int maxShield = 100; //default max value of shield
	static private int points = 0; //default points
	static private int numberOfBlaster = 3;
	static private int numberOfMissiles = 2;
	static private int numberOfLaser = 1;
	static private int numberOfBombs = 3;
	static private int powerBlaster = 400;
	static private int powerLaser = 200;
	static private int powerMissile = 175;
	static private int speedBlaster = 9;
	static private int speedMissile = 6;
	static private Map<String, Integer> weaponsMap; //map contains access to weapons level
	static private Map<String, Integer> weaponsMaxMap; //map contains information about max statistics for weapons
	static private boolean isNumberMax = false;//if bullets max number for all weapons is already reached
	static private boolean isSpeedMax = false; //if bullets max speed for all weapons is already reached

	private BufferedImage image;
	private String typeOfWeapon = "BLASTER";

	public Player() 
	{
		setMap();
	}

	//sets definition of a map, assign keys to values
	private void setMap()
	{
		//initiate map
		weaponsMap = new HashMap<String,Integer>();
		weaponsMaxMap = new HashMap<String, Integer>();

		//assign weapons statistics
		weaponsMap.put("numberOfBlaster", numberOfBlaster);
		weaponsMap.put("numberOfMissiles", numberOfMissiles);
		weaponsMap.put("numberOfLaser", numberOfLaser);
		weaponsMap.put("numberOfBombs", numberOfBombs);
		weaponsMap.put("powerBlaster", powerBlaster);
		weaponsMap.put("powerLaser", powerLaser);
		weaponsMap.put("powerMissile", powerMissile);
		weaponsMap.put("speedBlaster", speedBlaster);
		weaponsMap.put("speedMissile", speedMissile);
		weaponsMap.put("maxShield", maxShield);

		//sets information about few max statistics
		weaponsMaxMap.put("numberOfBlaster", 7);
		weaponsMaxMap.put("numberOfMissiles", 4);
		weaponsMaxMap.put("numberOfLaser", 3);
		weaponsMaxMap.put("numberOfBombs", 3);
		weaponsMaxMap.put("speedBlaster", 13);
		weaponsMaxMap.put("speedMissile", 9);
	}

	//sets if max number of bullets is reached
	public void setIfMaxNumber(boolean isMax)
	{
		Player.isNumberMax = isMax;
	}

	//sets if max speed of bullets is reached
	public void setIfMaxSpeed(boolean isMax)
	{
		Player.isSpeedMax = isMax;
	}

	//overrides value in a map
	private void overrideMapValue(String key, int value)
	{
		weaponsMap.put(key, value);
	}

	//methods for overriding values in a map and update values of variables
	public void setNumberOfBulletsBlaster(int number)
	{
		Player.numberOfBlaster = number;
		overrideMapValue("numberOfBlaster", number);
	}

	public void setNumberOfBulletsMissiles(int number)
	{
		Player.numberOfMissiles = number;
		overrideMapValue("numberOfMissiles", number);
	}

	public void setNumberOfBulletsLaser(int number)
	{
		Player.numberOfLaser = number;
		overrideMapValue("numberOfLaser", number);
	}

	public void setNumberOfBombs(int number)
	{
		Player.numberOfBombs = number;
		overrideMapValue("numberOfBombs", number);
	}

	public void setPowerBlaster(int power)
	{
		Player.powerBlaster = power;
		overrideMapValue("powerBlaster", power);
	}

	public void setPowerMissiles(int power)
	{
		Player.powerMissile = power;
		overrideMapValue("powerMissile", power);
	}

	public void setPowerLaser(int power)
	{
		Player.powerLaser = power;
		overrideMapValue("powerLaser", power);
	}

	public void setSpeedBlaster(int speed)
	{
		Player.speedBlaster = speed;
		overrideMapValue("speedBlaster", speed);
	}

	public void setSpeedMissile(int speed)
	{
		Player.speedMissile = speed;
		overrideMapValue("speedMissile", speed);
	}

	//sets new max shield
	public void setMaxShield(int newShield)
	{
		Player.maxShield = newShield;
	}

	//sets shield
	public void setShield(int shield)
	{
		Player.shield = shield;
	}

	//sets life
	public void setLife(int hp)
	{
		Player.hp = hp;
	}

	//sets result 
	public void setPoints(int points)
	{
		Player.points = points;
	}

	//sets image to paint on a scene
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	//sets used type of weapon
	public void setTypeOfWeapon(String typeOfWeapon)
	{
		this.typeOfWeapon = typeOfWeapon;
	}

	//gets center of an object in global coordinate system
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}

	//gets image to paint
	public BufferedImage getImage()
	{
		return image;
	}

	//information about weapons
	public int getInfo(String infoName)
	{
		return weaponsMap.get(infoName);
	}

	//gets maximum allowed value of weapon statistic
	public int getWeaponMaxInfo(String infoName)
	{
		return weaponsMaxMap.get(infoName);
	}

	//gets max shield value
	public int getMaxShield()
	{
		return maxShield;
	}

	//gets current shield value
	public int getShield()
	{
		return shield;
	}

	//gets amount of life
	public int getLife()
	{
		return hp;
	}

	//gets points of player
	public int getPoints()
	{
		return points;
	}

	//gets information about current type of weapon
	public String getTypeOfWeapon()
	{
		return typeOfWeapon;
	}

	//gets if max number of bullets reached
	public boolean getIfIsMaxNumber()
	{
		return isNumberMax;
	}

	//gets if bullets max speed reached
	public boolean getIfIsMaxSpeed()
	{
		return isSpeedMax;
	}
}
