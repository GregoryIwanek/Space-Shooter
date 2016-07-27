package ModelPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

public class PlayerModel 
{

	public PlayerModel(){}

	public void setShieldToDisplay(Player player, int points)
	{
		int shield = player.getPlayerShield();
		if (shield >= 0 && shield <= 100)
		{
			shield += points;

			if (shield < 0) shield = 0;
			else if (shield > 100) shield = 100;
		}

		player.setShield(shield);
	}
	public void setLifeToDisplay(Player player, int points)
	{
		int hp = player.getPlayerLife();

		if (player.getPlayerShield() == 0)
		{
			if (points < 0) hp += points;
			if (hp < 0) hp = 0;
		}

		player.setLife(hp);
	}

	public void calculateMovementOfPlayer(Player player, ArrayList<Integer> listOfPressedKeys)
	{
		//if contains LEFT BUTTON 
		if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(37) == true)
		{
			updateX(player, false);
			//and if contains UP/DOWN buttons, move LEFT UP/DOWN
			if (listOfPressedKeys.contains(38) == true) updateY(player, false);
			else if (listOfPressedKeys.contains(40) == true) updateY(player, true);
		}
		//if contains UP BUTTON
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(38) == true)
		{
			updateY(player, false);
			//and if contains LEFT/RIGHT button move UP LEFT/RIGHT
			if (listOfPressedKeys.contains(37) == true) updateX(player, false);
			else if (listOfPressedKeys.contains(39) == true) updateX(player, true);
		}
		//if contains RIGHT button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(39) == true)
		{
			updateX(player, true);
			//and if contains UP/DOWN button move RIGHT UP/DOWN
			if (listOfPressedKeys.contains(38) == true) updateY(player, false);
			else if (listOfPressedKeys.contains(40) == true) updateY(player, true);
		}
		//if contains DOWN button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(40) == true)
		{
			updateY(player, true);
			//and if contains LEFT/RIGHT button move DOWN LEFT/RIGHT
			if (listOfPressedKeys.contains(37) == true) updateX(player, false);
			else if (listOfPressedKeys.contains(39) == true) updateX(player, true);
		}
	}

	public void updateX(Player player, boolean isIncreasing)
	{
		if (isIncreasing == true && player.getLocation().x < 585)
		{
			player.setLocation(player.getLocation().x+5, player.getLocation().y);
		}
		else if (isIncreasing == false && player.getLocation().x > 0)
		{
			player.setLocation(player.getLocation().x-5, player.getLocation().y);
		}
	}

	public void updateY(Player player, boolean isIncreasing)
	{
		if (isIncreasing == true && player.getLocation().y  < 585)
		{
			player.setLocation(player.getLocation().x, player.getLocation().y+5);
		}
		else if (isIncreasing == false && player.getLocation().y > 0)
		{
			player.setLocation(player.getLocation().x, player.getLocation().y-5);
		}
	}
	public void updatePoints(Player player, int points)
	{
		int newPoints = player.getPoints();
		player.setPoints(newPoints + points);
	}
	public void setImageOfPlayer(Player player, String path)
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		player.setImageOfPlayer(image);
	}
	public void setSizeOfPlayer(Player player, Dimension size)
	{
		player.setSize(size);
	}
	//////
	public Point getNewPosition(Player player)
	{
		return player.getLocation();
	}
	public Point getCenter(Player player)
	{
		int xCenter = player.getLocation().x + player.getBounds().width/2;
		int yCenter = player.getLocation().y + player.getBounds().height/2;
		return new Point(xCenter, yCenter);
	}
	public int getPlayersShield(Player player)
	{
		return player.getPlayerShield();
	}
	public int getPlayersLife(Player player)
	{
		return player.getPlayerLife();
	}
	public int getPlayersPoints(Player player)
	{
		return player.getPoints();
	}
}
