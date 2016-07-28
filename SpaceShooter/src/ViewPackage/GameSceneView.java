package ViewPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.sound.sampled.Line;
import javax.swing.JPanel;

import ModelPackage.Bullet;
import ModelPackage.Enemy;
import ModelPackage.Player;

public class GameSceneView extends JPanel
{
	static private Player player;
	static private ArrayList<Enemy> listOfEnemyShips;
	static private ArrayList<Bullet> listOfBullets;
	static private ArrayList<Bullet> listOfPlayerBullets;
	static private Color colorBullet;
	static private Color colorBulletPlayer;

	public GameSceneView(int width, int height) 
	{
		setPreferredSize(new Dimension(width, height));

		colorBullet = new Color(255,0,0);
		colorBulletPlayer = new Color(255,255,0);

		player = new Player();

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
	public void setPlayer (Player player)
	{
		GameSceneView.player = player;
	}

	@Override public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(colorBullet);

		g2.drawImage(player.getImageOfPlayer(), player.getLocation().x, player.getLocation().y,
				player.getSize().width, player.getSize().height, null);
	
		for (Enemy enemy : listOfEnemyShips)
		{
			g2.drawImage(enemy.getImageOfEnemy(), enemy.getLocation().x, enemy.getLocation().y,
					enemy.getSize().width, enemy.getSize().height, null);
		}

		for (Bullet bullet : listOfBullets)
		{
			g2.fillRect(bullet.getLocation().x, bullet.getLocation().y, bullet.getSize().width, bullet.getSize().height);
		}

		g2.setColor(colorBulletPlayer);
		for (Bullet bullet : listOfPlayerBullets)
		{
			g2.fillRect(bullet.getLocation().x, bullet.getLocation().y, bullet.getSize().width, bullet.getSize().height);
		}
	}
}


