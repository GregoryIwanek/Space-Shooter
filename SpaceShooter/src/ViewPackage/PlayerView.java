package ViewPackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerView extends Rectangle
{
	private int x;
	private int y;
	private int shield = 100, hp = 100;
	private BufferedImage imageOfPlayer;

	public PlayerView() 
	{
		x = 200;
		y = 500;
		this.setSize(new Dimension(45, 65));
		setImageOfPlayer();
	}

	public void setImageOfPlayer()
	{
		try {
			imageOfPlayer = ImageIO.read(getClass().getResourceAsStream("/Player.png"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public void setPosition(int x, int y)
	{
		//this.x = x;
		//this.y = y;
	}
	public Point getCenter()
	{
		return new Point(this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);
	}

	public BufferedImage getImageOfPlayer()
	{
		return imageOfPlayer;
	}

}
