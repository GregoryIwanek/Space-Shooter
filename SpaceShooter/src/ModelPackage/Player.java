package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Rectangle
{
	private int shield = 100, hp = 100; //default players shield and life
	private int maxShield = 100; //default max value of shield
	private int points = 0; //default points


	private int numberOfBlaster = 3;
	private int numberOfMissiles = 2;
	private int numberOfLaser = 1;
	private int numberOfBombs = 3;
	private int powerBlaster = 400;
	private int powerLaser = 200;
	private int powerMissile = 175;
	private int speedBlaster = 9;
	private int speedMissile = 6;
	private Map<String, Integer> weaponsMap; //map contains access to weapons level
	private Map<String, Integer> weaponsMaxMap; //map contains information about max statistics for weapons
	private boolean isNumberMax = false;//if bullets max number for all weapons is already reached
	private boolean isSpeedMax = false; //if bullets max speed for all weapons is already reached

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

		//sets information about few max statistics
		weaponsMaxMap.put("numberOfBlaster", 7);
		weaponsMaxMap.put("numberOfMissiles", 4);
		weaponsMaxMap.put("numberOfLaser", 3);
		weaponsMaxMap.put("numberOfBombs", 3);
		weaponsMaxMap.put("speedBlaster", 13);
		weaponsMaxMap.put("speedMissile", 9);
	}
	
	public void setIfMaxNumber(boolean isMax)
	{
		this.isNumberMax = isMax;
		System.out.println(isNumberMax);
	}
	
	public void setIfMaxSpeed(boolean isMax)
	{
		this.isSpeedMax = isMax;
	}

	private void overrideMapValue(String key, int value)
	{
		weaponsMap.put(key, value);
	}

	public void setNumberOfBulletsBlaster(int number)
	{
		this.numberOfBlaster = number;
		overrideMapValue("numberOfBlaster", number);
	}

	public void setNumberOfBulletsMissiles(int number)
	{
		this.numberOfMissiles = number;
		overrideMapValue("numberOfMissiles", number);
	}

	public void setNumberOfBulletsLaser(int number)
	{
		this.numberOfLaser = number;
		overrideMapValue("numberOfLaser", number);
	}

	public void setNumberOfBombs(int number)
	{
		this.numberOfBombs = number;
		overrideMapValue("numberOfBombs", number);
	}

	public void setPowerBlaster(int power)
	{
		this.powerBlaster = power;
		overrideMapValue("powerBlaster", power);
	}

	public void setPowerMissiles(int power)
	{
		this.powerMissile = power;
		overrideMapValue("powerMissile", power);
	}

	public void setPowerLaser(int power)
	{
		this.powerLaser = power;
		overrideMapValue("powerLaser", power);
	}

	public void setSpeedBlaster(int speed)
	{
		this.speedBlaster = speed;
		overrideMapValue("speedBlaster", speed);
	}

	public void setSpeedMissile(int speed)
	{
		this.speedMissile = speed;
		overrideMapValue("speedMissile", speed);
	}

	public void setMaxShield(int newShield)
	{
		this.maxShield = newShield;
	}

	//sets shield
	public void setShield(int shield)
	{
		this.shield = shield;
	}

	//sets life
	public void setLife(int hp)
	{
		this.hp = hp;
	}

	//sets result 
	public void setPoints(int points)
	{
		this.points = points;
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
	public int getWeaponInfo(String infoName)
	{
		return weaponsMap.get(infoName);
	}

	//gets maximum allowed value of weapon statistic
	public int getWeaponMaxInfo(String infoName)
	{
		return weaponsMaxMap.get(infoName);
	}

	public int getMaxShield()
	{
		return maxShield;
	}

	//gets power of shield
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
	
	public boolean getIfIsMaxNumber()
	{
		return isNumberMax;
	}
	
	public boolean getIfIsMaxSpeed()
	{
		return isSpeedMax;
	}
}
