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
	
	
	private int numberOfBlaster = 2;
	private int numberOfMissiles = 2;
	private int numberOfLaser = 1;
	private int numberOfBombs = 1;
	private int powerBlaster = 500;
	private int powerLaser = 100;
	private int powerMissile = 150;
	private int speedBlaster = 9;
	private int speedMissile = 6;
	private Map<String, Integer> weaponsMap; //map contains access to weapons level
	
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
		
		//assign number of bullets
		weaponsMap.put("numberOfBlaster", numberOfBlaster);
		weaponsMap.put("numberOfMissiles", numberOfMissiles);
		weaponsMap.put("numberOfLaser", numberOfLaser);
		weaponsMap.put("numberOfBombs", numberOfBombs);
		weaponsMap.put("powerBlaster", powerBlaster);
		weaponsMap.put("powerLaser", powerLaser);
		weaponsMap.put("powerMissile", powerMissile);
		weaponsMap.put("speedBlaster", speedBlaster);
		weaponsMap.put("speedMissile", speedMissile);
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
}
