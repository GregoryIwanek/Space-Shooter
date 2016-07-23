package ModelPackage;

public class BulletsModel 
{
	int xCurrentPosition;
	int yCurrentPosition;
	double deltaX, deltaY;
	
	public BulletsModel() 
	{
		
	}
	
	public void calculateMovementAsBlaster()
	{
		
	}
	
	public void calculateMovementAsMisile()
	{
		
	}
	
	public void calculateMovementAsLaser()
	{
		
	}
	
	public void calculateMovementOfBullet(int xEnemy, int yEnemy, int xPlayer, int yPlayer)
	{
		double angle = Math.atan2(xPlayer - xEnemy, yPlayer - yEnemy);
		 deltaX = Math.sin(angle);
		 deltaY = Math.cos(angle);
	}

	public void updateX()
	{
		xCurrentPosition +=5;
	}
	
	public void updateY()
	{
		yCurrentPosition += 5;
	}
}
