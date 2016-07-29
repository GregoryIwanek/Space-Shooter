package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Rectangle
{
	private int shield = 100, hp = 100; //default players shield and life
	private int points = 0; //default points
	private BufferedImage imageOfPlayer;
	private String typeOfWeapon = "BLASTER";
	private int lvlOfBlaster = 1; //default level of weapon
	private int lvlOfLaser = 1; 
	private int lvlOfMisiles = 1;
	private Map<String, Integer> levelOfWeaponsMap; //map contains access to weapons level

	public Player() 
	{
		setMap();
	}
	
	//sets definition of a map
	private void setMap()
	{
		levelOfWeaponsMap = new HashMap<String,Integer>();
		levelOfWeaponsMap.put("lvlOfBlaster", lvlOfBlaster);
		levelOfWeaponsMap.put("lvlOfLaser", lvlOfLaser);
		levelOfWeaponsMap.put("lvlOfMisiles", lvlOfMisiles);
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
	public void setImageOfPlayer(BufferedImage imageOfPlayer)
	{
		this.imageOfPlayer = imageOfPlayer;
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
	public BufferedImage getImageOfPlayer()
	{
		return imageOfPlayer;
	}
	
	//gets level of weapon
	public int getLevelOfWeapon(String weaponName)
	{
		return levelOfWeaponsMap.get(weaponName);
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
