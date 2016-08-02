package ViewPackage;
import java.awt.*;
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
	private JPanel weaponsInfoPanel;
	private JPanel statusPanel;
	private JPanel menuPanel;
	private GameSceneView gameScenePanel;

	//buttons
	private JButton buttonStart;
	private JButton buttonBack;
	private JButton buttonWeaponMissiles;
	private JButton buttonWeaponLaser;
	private JButton buttonWeaponBlaster;
	private JButton buttonWeaponBomb;

	//labels
	//player status
	private JLabel labelHP;
	private JLabel labelShield;
	private JLabel labelPoints;
	private JLabel labelLevel;
	//info
	private JLabel labelLaserNumber;
	private JLabel labelLaserPower;
	private JLabel labelBlasterNumber;
	private JLabel labelBlasterPower;
	private JLabel labelBlasterSpeed;
	private JLabel labelMissileNumber;
	private JLabel labelMissilePower;
	private JLabel labelMissileSpeed;
	private JLabel labelBombNumber;
	private JLabel labelShieldMax;

	//data container
	private Map<String,JLabel> labelMap;

	//IMPLEMENTATION
	//------------------------------------------------------------------------------------------------------------------------------------
	public GameInterfaceView()
	{
		//sets size of parent panel
		this.setPreferredSize(new Dimension(800,800));

		//sets interface elements in panel
		setsButtons();
		setsLabels();
		setsPanels();
		fillWindow();
	}

	private void setsButtons()
	{
		//setting buttons in game interface
		buttonStart = new JButton("START");
		defineButton(buttonStart, "START", new Dimension(120,40), false);

		buttonBack = new JButton("BACK");
		defineButton(buttonBack, "BACK", new Dimension(120,40), false);

		buttonWeaponMissiles = new JButton("MISSILES");
		defineButton(buttonWeaponMissiles, "MISSILES", new Dimension(120,100), false);

		buttonWeaponLaser = new JButton("LASER");
		defineButton(buttonWeaponLaser, "LASER", new Dimension(120,100), false);

		buttonWeaponBlaster = new JButton("BLASTER");
		defineButton(buttonWeaponBlaster, "BLASTER", new Dimension(120,100), false);

		buttonWeaponBomb = new JButton("BOMB");
		defineButton(buttonWeaponBomb , "BOMB", new Dimension(120,100), false);
	}

	//define buttons of game interface menu
	public void defineButton(JButton button, String name, Dimension size, boolean makeFocusable)
	{
		button.setName(name);
		button.setPreferredSize(size);
		button.setFocusable(makeFocusable);
	}

	//sets labels of game interface menu (info texts)
	private void setsLabels()
	{
		//initiation of map container
		labelMap = new HashMap<String,JLabel>();

		//initiation of labels
		labelPoints = new JLabel();
		labelHP = new JLabel();
		labelLevel = new JLabel();
		labelShield = new JLabel();
		labelMissileNumber = new JLabel();
		labelMissilePower = new JLabel();
		labelMissileSpeed = new JLabel();
		labelBlasterNumber = new JLabel();
		labelBlasterPower = new JLabel();
		labelBlasterSpeed = new JLabel();
		labelLaserNumber = new JLabel();
		labelLaserPower = new JLabel();
		labelBombNumber = new JLabel();
		labelShieldMax = new JLabel();
		
		//setting labels with information and adding them to map
		//player status
		defineLabels(labelPoints, "Points 0", "labelPoints");
		defineLabels(labelHP, "HP 100", "labelHP");
		defineLabels(labelLevel, "Level 1", "labelLevel");
		defineLabels(labelShield, "Shield 100", "labelShield");
		//player statistic info
		defineLabels(labelLaserNumber, "Laser 1", "labelLaserNumber");
		defineLabels(labelLaserPower, "Laser power: 100", "labelLaserPower");
		defineLabels(labelBlasterNumber, "Blaster 2", "labelBlasterNumber");
		defineLabels(labelBlasterPower, "Blaster power: 500", "labelBlasterPower");
		defineLabels(labelBlasterSpeed, "Blaster speed 9", "labelBlasterSpeed");
		defineLabels(labelMissileNumber, "Missile 2", "labelMissileNumber");
		defineLabels(labelMissilePower, "Missile power: 150", "labelMissilePower");
		defineLabels(labelMissileSpeed, "Missile speed 7", "labelMissileSpeed");
		defineLabels(labelBombNumber, "Bomb 1", "labelBombNumber");
		defineLabels(labelShieldMax, "Shield max 100", "labelShieldMax");
	}

	private void defineLabels(JLabel label, String text, String mapKey)
	{
		label.setText(text);
		labelMap.put(mapKey, label);
	}

	//updating labels texts, checked every trigger of Timer in controller
	public void updateLabels(String name, int value)
	{
		//sets text to display
		String textToDisplay = name.substring(5);
		labelMap.get(name).setText(textToDisplay +" "+ new Integer(value).toString());
	}

	//setting sup-panels of this panel
	private void setsPanels()
	{
		//setting panels and adding components to them
		//weapons panel, contains buttons with possible choice of weapons
		weaponsPanel = new JPanel();
		weaponsPanel.setPreferredSize(new Dimension (130,650));
		weaponsPanel.setBackground(Color.red);
		weaponsPanel.add(buttonWeaponMissiles);
		weaponsPanel.add(buttonWeaponBlaster);
		weaponsPanel.add(buttonWeaponLaser);
		weaponsPanel.add(buttonWeaponBomb);

		//status panel, contains labels to show information
		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(630, 100));
		statusPanel.setBackground(Color.green);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
		statusPanel.add(labelPoints, BorderLayout.EAST);
		statusPanel.add(labelLevel, BorderLayout.EAST);
		statusPanel.add(labelHP, BorderLayout.EAST);
		statusPanel.add(labelShield, BorderLayout.EAST);

		//menu panel, contains button start and back
		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(130, 100));
		menuPanel.setBackground(Color.yellow);
		menuPanel.add(buttonStart);
		menuPanel.add(buttonBack);

		//weapon info panel
		weaponsInfoPanel = new JPanel();
		weaponsInfoPanel.setPreferredSize(new Dimension(120, 250));
		weaponsInfoPanel.setBackground(Color.red);
		weaponsInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		weaponsInfoPanel.add(labelMissileNumber);
		weaponsInfoPanel.add(labelMissilePower);
		weaponsInfoPanel.add(labelMissileSpeed);
		weaponsInfoPanel.add(labelBlasterNumber);
		weaponsInfoPanel.add(labelBlasterPower);
		weaponsInfoPanel.add(labelBlasterSpeed);
		weaponsInfoPanel.add(labelLaserNumber);
		weaponsInfoPanel.add(labelLaserPower);
		weaponsInfoPanel.add(labelBombNumber);
		weaponsInfoPanel.add(labelShieldMax);


		weaponsPanel.add(weaponsInfoPanel);

		//game scene panel, here we pain our game
		gameScenePanel = new GameSceneView(630, 650);
		gameScenePanel.setPreferredSize(new Dimension(630, 650));
		gameScenePanel.setBackground(new Color(8445951));
	}

	//adding sub-panels to main panel
	private void fillWindow()
	{
		//adds panels to view
		this.add(gameScenePanel, BorderLayout.PAGE_START);
		this.add(weaponsPanel, BorderLayout.EAST);
		this.add(statusPanel);
		this.add(menuPanel);
	}

	//set action listener for buttons in all panels
	public void addActionListenerGameInterface(ActionListener actionListener)
	{
		//sets listeners to buttons in game interface
		buttonStart.addActionListener(actionListener);
		buttonBack.addActionListener(actionListener);
		buttonWeaponMissiles.addActionListener(actionListener);
		buttonWeaponBlaster.addActionListener(actionListener);
		buttonWeaponBomb.addActionListener(actionListener);
		buttonWeaponLaser.addActionListener(actionListener);
	}

	//adds key listener for THIS main panel, it will read pressed/released keys
	public void addKeyListenerGameInterface(KeyListener keyListenerGameInterface)
	{
		this.addKeyListener(keyListenerGameInterface);
	}

	//GETTERS
	//--------------------------------------------------------------------------------------------------------------------------------------

	//gets label to display new information
	public JLabel getLabel(String labelToGet)
	{
		return labelMap.get(labelToGet);
	}

	//gets scene of a game
	public GameSceneView getScenePanel()
	{
		return gameScenePanel;
	}
}
