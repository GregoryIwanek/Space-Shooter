package ModelPackage;

import java.awt.Point;
import java.util.ArrayList;

public class PlayerModel 
{
	private int xCurrentPosition = 0;
	private int yCurrentPosition = 0;
	private int width, height;
	private int shield = 100, hp = 100;
	
	public PlayerModel(int xCurrentPosition, int yCurrentPosition)
	{
		this.xCurrentPosition = xCurrentPosition;
		this.yCurrentPosition = yCurrentPosition;
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setShieldToDisplay(int points)
	{
		if (shield >= 0 && shield <= 100)
		{
			shield += points;
			
			if (shield < 0) shield = 0;
			else if (shield > 100) shield = 100;
		}
	}
	public void setLifeToDisplay(int points)
	{
		if (shield == 0)
		{
			if (points < 0)
			{
				hp += points;
			}		
		}
		if (hp < 0)
		{
			hp = 0;
		}
	}
	
	public void calculateMovementOfPlayer(ArrayList<Integer> listOfPressedKeys)
	{
		//if contains LEFT BUTTON 
		if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(37) == true)
		{
			updateX(false);
			//and if contains UP/DOWN buttons
			if (listOfPressedKeys.contains(38) == true) updateY(false);
			else if (listOfPressedKeys.contains(40) == true) updateY(true);
		}
		//if contains UP BUTTON
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(38) == true)
		{
			updateY(false);
			//and if contains LEFT/RIGHT button
			if (listOfPressedKeys.contains(37) == true) updateX(false);
			else if (listOfPressedKeys.contains(39) == true) updateX(true);
		}
		//if contains RIGHT button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(39) == true)
		{
			updateX(true);
			//and if contains UP/DOWN button
			if (listOfPressedKeys.contains(38) == true) updateY(false);
			else if (listOfPressedKeys.contains(40) == true) updateY(true);
		}
		//if contains DOWN button
		else if (listOfPressedKeys.size() < 3 && listOfPressedKeys.contains(40) == true)
		{
			updateY(true);
			//and if contains LEFT/RIGHT button
			if (listOfPressedKeys.contains(37) == true) updateX(false);
			else if (listOfPressedKeys.contains(39) == true) updateX(true);
		}
	}
	
	public void updateX(boolean isIncreasing)
	{
		if (isIncreasing == true && xCurrentPosition < 585)
		{
			xCurrentPosition += 5;
		}
		else if (isIncreasing == false && xCurrentPosition > 0)
		{
			xCurrentPosition -= 5;
		}
	}
	public void updateY(boolean isIncreasing)
	{
		if (isIncreasing == true && yCurrentPosition < 585)
		{
			yCurrentPosition += 5;
		}
		else if (isIncreasing == false && yCurrentPosition > 0)
		{
			yCurrentPosition -= 5;
		}
	}
	public void updatePosition()
	{
		
	}
	
	public Point getNewPosition()
	{
		return new Point(xCurrentPosition, yCurrentPosition);
	}
	
	public Point getCenter()
	{
		return new Point(xCurrentPosition+width/2, yCurrentPosition+height/2);
	}
	public int getShield()
	{
		return shield;
	}
	public int getLife()
	{
		return hp;
	}
}
