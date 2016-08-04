package ModelPackage;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Bonus extends Rectangle
{
	private String type = "NONE";

	//values of bonuses
	static private int hpRestored = 10;
	static private int shieldRestored = 20;
	static private int shieldPernament = 10;
	static private int extraBulletBlaster = 2;
	static private int extraBulletLaser = 1;
	static private int extraBulletMissile = 1;
	static private int extraBomb = 1;
	static private int extraBlasterPower = 100;
	static private int extraLaserPower = 10;
	static private int extraMissilePower = 50;
	static private int extraBlasterSpeed = 1;
	static private int extraMissileSpeed = 1;
	static private Map<String, Integer> informationMap; //map contains access to weapons level

	static private BufferedImage image;

	public Bonus()
	{
		setMap();
	}

	//sets map with information about bonus
	public void setMap()
	{
		//initiate map
		informationMap = new HashMap<String,Integer>();
		
		//sets information to a map
		informationMap.put("hpRestored", hpRestored);
		informationMap.put("shieldRestored", shieldRestored);
		informationMap.put("shieldPernament", shieldPernament);
		informationMap.put("extraBulletBlaster", extraBulletBlaster);
		informationMap.put("extraBulletLaser", extraBulletLaser);
		informationMap.put("extraBulletMissile", extraBulletMissile);
		informationMap.put("extraBomb", extraBomb);
		informationMap.put("extraBlasterPower", extraBlasterPower);
		informationMap.put("extraLaserPower", extraLaserPower);
		informationMap.put("extraMissilePower", extraMissilePower);
		informationMap.put("extraBlasterSpeed", extraBlasterSpeed);
		informationMap.put("extraMissileSpeed", extraMissileSpeed);
	}

	//sets type of bonus (weapon upgrade, heal, etc)
	public void setType(String type)
	{
		this.type = type;
	}

	//sets image of this item to paint on scene
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	//gets int value of bonus from map
	public int getInformationFromMap(String key)
	{
		return informationMap.get(key);
	}
	
	//gets type of this bonus object
	public String getType()
	{
		return type;
	}
	
	//gets image of bonus for painting it on scene
	public BufferedImage getImage()
	{
		return image;
	}
}
