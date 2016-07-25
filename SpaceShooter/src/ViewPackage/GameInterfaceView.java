package ViewPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameInterfaceView extends JPanel
{
	//DATA
	//------------------------------------------------------------------------------------------------------------------------------------
	//panels
	private JPanel weaponsPanel;
	private JPanel statusPanel;
	private JPanel menuPanel;
	private GameSceneView gameScenePanel;

	//buttons
	private JButton buttonStart;
	private JButton buttonBack;
	private JButton buttonWeaponMisiles;
	private JButton buttonWeaponLaser;
	private JButton buttonWeaponBlaster;
	private JButton buttonWeaponBomb;

	//labels
	private JLabel labelHP;
	private JLabel labelShield;
	private JLabel labelPoints;
	private JLabel labelLevel;

	//data container
	private Map<String,JLabel> labelMap;

	//IMPLEMENTATION
	//------------------------------------------------------------------------------------------------------------------------------------
	public GameInterfaceView()
	{
		//sets size of parent panel
		this.setPreferredSize(new Dimension(800,800));

		setsButtons();
		setsLabels();
		setsPanels();
		fillWindow();
	}

	private void setsButtons()
	{
		//setting buttons
		buttonStart = new JButton("START");
		defineButton(buttonStart, "START", new Dimension(120,40), false);

		buttonBack = new JButton("BACK");
		defineButton(buttonBack, "BACK", new Dimension(120,40), false);

		buttonWeaponMisiles = new JButton("MISILES");
		defineButton(buttonWeaponMisiles, "MISILES", new Dimension(120,120), false);
		
		buttonWeaponLaser = new JButton("LASER");
		defineButton(buttonWeaponLaser, "LASER", new Dimension(120,120), false);
		
		buttonWeaponBlaster = new JButton("BLASTER");
		defineButton(buttonWeaponBlaster, "BLASTER", new Dimension(120,120), false);
		
		buttonWeaponBomb = new JButton("BOMB");
		defineButton(buttonWeaponBomb , "BOMB", new Dimension(120,120), false);
	}
	
	public void defineButton(JButton button, String name, Dimension size, boolean makeFocusable)
	{
		button.setName(name);
		button.setPreferredSize(size);
		button.setFocusable(makeFocusable);
	}

	private void setsLabels()
	{
		//initiation of map container
		labelMap = new HashMap<String,JLabel>();

		//setting labels and adding them to map
		labelPoints = new JLabel("0");
		labelMap.put("labelPoints", labelPoints);

		labelHP = new JLabel("100");
		labelMap.put("labelHP", labelHP);

		labelLevel = new JLabel("1");
		labelMap.put("labelLevel", labelLevel);

		labelShield = new JLabel("100");
		labelMap.put("labelShield", labelShield);
	}
	
	public void updateLabels(String name, int value)
	{
		String textToDisplay = name.substring(5);
		labelMap.get(name).setText(textToDisplay +" "+ new Integer(value).toString());
	}

	private void setsPanels()
	{
		//setting panels and adding components to them
		//weapons panel
		weaponsPanel = new JPanel();
		weaponsPanel.setPreferredSize(new Dimension (130,650));
		weaponsPanel.setBackground(Color.red);
		weaponsPanel.add(buttonWeaponMisiles);
		weaponsPanel.add(buttonWeaponBlaster);
		weaponsPanel.add(buttonWeaponLaser);
		weaponsPanel.add(buttonWeaponBomb);

		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(630, 100));
		statusPanel.setBackground(Color.green);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
		statusPanel.add(labelPoints, BorderLayout.EAST);
		statusPanel.add(labelLevel, BorderLayout.EAST);
		statusPanel.add(labelHP, BorderLayout.EAST);
		statusPanel.add(labelShield, BorderLayout.EAST);

		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(130, 100));
		menuPanel.setBackground(Color.yellow);
		menuPanel.add(buttonStart);
		menuPanel.add(buttonBack);

		gameScenePanel = new GameSceneView(630, 650);
		gameScenePanel.setPreferredSize(new Dimension(630, 650));
		gameScenePanel.setBackground(new Color(8445951));
	}

	private void fillWindow()
	{
		//adds panels to view
		this.add(gameScenePanel, BorderLayout.PAGE_START);
		this.add(weaponsPanel, BorderLayout.EAST);
		this.add(statusPanel);
		this.add(menuPanel);
	}

	public void addActionListenerGameInterface(ActionListener actionListener)
	{
		//sets listeners for buttons
		buttonStart.addActionListener(actionListener);
		buttonBack.addActionListener(actionListener);
		buttonWeaponMisiles.addActionListener(actionListener);
	}
	
	public void addKeyListenerGameInterface(KeyListener keyListenerGameInterface)
	{
		this.addKeyListener(keyListenerGameInterface);
	}

	//GETTERS
	//--------------------------------------------------------------------------------------------------------------------------------------
	public JLabel getLabel(String labelToGet)
	{
		return labelMap.get(labelToGet);
	}
	public GameSceneView getScenePanel()
	{
		return gameScenePanel;
	}
}
