package ModelPackage;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Bonus extends Rectangle
{
	private String typeOfBouns = "NONE";

	private int hpRestored = 10;
	private int shieldRestored = 20;
	private int shieldPernament = 10;
	private int extraBulletBlaster = 1;
	private int extraBulletLaser = 1;
	private int extraBulletMissile = 1;
	private int extraBomb = 1;
	private int extraBlasterPower = 100;
	private int extraLaserPower = 10;
	private int extraMissilePower = 50;
	private int extraBlasterSpeed = 1;
	private int extraMissileSpeed = 1;
	private Map<String, Integer> informationMap; //map contains access to weapons level

	private BufferedImage imageOfBonus;

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
	public void setTypeOfBonus(String typeOfBonus)
	{
		this.typeOfBouns = typeOfBonus;
	}

	//sets image of this item to paint on scene
	public void setImage(BufferedImage imageOfBonus)
	{
		this.imageOfBonus = imageOfBonus;
	}
	
	//gets int value of bonus from map
	public int getInformationFromMap(String key)
	{
		return informationMap.get(key);
	}
	
	//gets type of this bonus object
	public String getTypeOfBonus()
	{
		return typeOfBouns;
	}
	
	//gets image of bonus for painting it on scene
	public BufferedImage getImageOfBonus()
	{
		return imageOfBonus;
	}
}
