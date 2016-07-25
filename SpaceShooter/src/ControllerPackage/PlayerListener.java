package ControllerPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import ModelPackage.PlayerModel;
import ModelPackage.Player;

public class PlayerListener implements ActionListener, KeyListener
{
	private PlayerModel playerModel;
	private Player playerView;
	
	private Map<String, Dimension> directionMap;
	
	public PlayerListener() 
	{
		//initiation of map container
		directionMap = new HashMap<String,Dimension>();
		
		//initiation of timer
		//timer = new Timer(20, this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP)
		{
			System.out.println(125);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}
