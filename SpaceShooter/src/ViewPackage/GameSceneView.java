package ViewPackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import ModelPackage.Bullet;
import ModelPackage.Enemy;
import ModelPackage.Player;

public class GameSceneView extends JPanel
{
	static private Player playerView;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;
	
	public GameSceneView(int width, int height) 
	{
		this.setPreferredSize(new Dimension(width, height));
		
		playerView = new Player();
		
		listOfEnemyShips = new ArrayList<Enemy>();
		listOfBullets = new ArrayList<Bullet>();
		listOfPlayerBullets = new ArrayList<Bullet>();
	}
	
	public void updateListOfEnemyShips(ArrayList<Enemy> listOfEnemyShips)
	{
		GameSceneView.listOfEnemyShips = listOfEnemyShips;
	}
	public void updateListOfBullets(ArrayList<Bullet> listOfBullets)
	{
		GameSceneView.listOfBullets = listOfBullets;
	}
	public void updateListOfPlayerBullets(ArrayList<Bullet> listOfPlayerBullets)
	{
		GameSceneView.listOfPlayerBullets = listOfPlayerBullets;
	}
	public void updatePlayerViewPosition(Point position)
	{
		playerView.setLocation(position);
	}
	
	@Override public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(playerView.getImageOfPlayer(), playerView.getLocation().x, playerView.getLocation().y,
				playerView.getSize().width, playerView.getSize().height, null);
		
		for (Enemy enemy : listOfEnemyShips)
		{
			g2.drawImage(enemy.getImageOfEnemy(), enemy.getLocation().x, enemy.getLocation().y,
					enemy.getSize().width, enemy.getSize().height, null);
		}
		
		for (Bullet bullet : listOfBullets)
		{
			g2.drawImage(bullet.getImageOfBullet(), bullet.getLocation().x, bullet.getLocation().y,
					bullet.getSize().width, bullet.getSize().height, null);
		}
		
		for (Bullet bullet : listOfPlayerBullets)
		{
			g2.drawImage(bullet.getImageOfBullet(), bullet.getLocation().x, bullet.getLocation().y,
					bullet.getSize().width, bullet.getSize().height, null);
		}
	}
	
	public Player getPlayerViewView()
	{
		return playerView;
	}
}
