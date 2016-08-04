package ViewPackage;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import ModelPackage.*;

public class GameSceneView extends JPanel
{
	//variables and containers for painted objects on a scene (panel)
	static private Player player;
	static private String gameOver;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfEnemyBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;
	static private ArrayList<Bonus> listOfBonuses;
	static private Color colorBullet;
	static private Color colorBulletPlayer;

	public GameSceneView(int width, int height) 
	{
		setPreferredSize(new Dimension(width, height));

		//initiate colors for painting
		colorBullet = new Color(255,0,0);
		colorBulletPlayer = new Color(255,255,0);
		gameOver = "";

		player = new Player();

		//initiate lists with objects
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfEnemyBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
		listOfBonuses = new ArrayList<Bonus>();
	}

	//methods for updating status of objects on a list
	public void updateListOfEnemyShips(ArrayList<Enemy> listOfEnemyShips)
	{
		GameSceneView.listOfEnemyShips = listOfEnemyShips;
	}
	public void updatelistOfEnemyBullets(ArrayList<Bullet> listOfEnemyBullets)
	{
		GameSceneView.listOfEnemyBullets = listOfEnemyBullets;
	}
	public void updateListOfPlayerBullets(ArrayList<Bullet> listOfPlayerBullets)
	{
		GameSceneView.listOfPlayerBullets = listOfPlayerBullets;
	}
	public void updateListOfBonuses(ArrayList<Bonus> listOfBonuses)
	{
		GameSceneView.listOfBonuses = listOfBonuses;
	}
	public void setPlayer (Player player)
	{
		GameSceneView.player = player;
	}
	public void setGameOverString(String string)
	{
		GameSceneView.gameOver = string;
	}

	//method for drawing current status on a scene
	@Override public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(colorBullet);

		//paint player
		g2.drawImage(player.getImage(), player.getLocation().x, player.getLocation().y,
				player.getSize().width, player.getSize().height, null);

		//draw text "GAME OVER"- string isn't empty only if players HP is equal zero
		g2.drawString(gameOver, this.getSize().width/2-25, this.getSize().height/2);

		//paint enemy ships
		for (Enemy enemy : listOfEnemyShips)
		{
			g2.drawImage(enemy.getImage(), enemy.getLocation().x, enemy.getLocation().y,
					enemy.getSize().width, enemy.getSize().height, null);
		}

		//paint enemy bullets
		for (Bullet bullet : listOfEnemyBullets)
		{
			g2.fillRect(bullet.getLocation().x, bullet.getLocation().y, bullet.getSize().width, bullet.getSize().height);
		}

		//paint player bullets
		g2.setColor(colorBulletPlayer);
		for (Bullet bullet : listOfPlayerBullets)
		{
			g2.fillRect(bullet.getLocation().x, bullet.getLocation().y, bullet.getSize().width, bullet.getSize().height);
		}

		//paint bonuses
		for (Bonus bonus : listOfBonuses)
		{
			g2.drawImage(bonus.getImage(), bonus.getLocation().x, bonus.getLocation().y,
					bonus.getSize().width, bonus.getSize().height, null);
		}
	}
}


