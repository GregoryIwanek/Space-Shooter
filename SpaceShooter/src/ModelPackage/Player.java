package ModelPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Rectangle
{
	private int shield = 100, hp = 100;
	private int points = 0;
	private BufferedImage imageOfPlayer;
	private int lvlOfBlaster = 1;
	private int lvlOfLaser = 1;
	private int lvlOfMisiles = 1;
	private Map<String, Integer> levelOfWeaponsMap;

	public Player() 
	{
		setMap();
	}
	private void setMap()
	{
		levelOfWeaponsMap = new HashMap<String,Integer>();
		levelOfWeaponsMap.put("lvlOfBlaster", lvlOfBlaster);
		levelOfWeaponsMap.put("lvlOfLaser", lvlOfLaser);
		levelOfWeaponsMap.put("lvlOfMisiles", lvlOfMisiles);
	}
	public void setShield(int shield)
	{
		this.shield = shield;
	}
	public void setLife(int hp)
	{
		this.hp = hp;
	}
	public void setPoints(int points)
	{
		this.points = points;
	}
	public void setImageOfPlayer(BufferedImage imageOfPlayer)
	{
		this.imageOfPlayer = imageOfPlayer;
	}
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}

	public BufferedImage getImageOfPlayer()
	{
		return imageOfPlayer;
	}
	public int getLevelOfWeapon(String weaponName)
	{
		return levelOfWeaponsMap.get(weaponName);
	}
	public int getPlayerShield()
	{
		return shield;
	}
	public int getPlayerLife()
	{
		return hp;
	}
	public int getPoints()
	{
		return points;
	}
}
