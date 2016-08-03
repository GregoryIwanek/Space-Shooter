package ViewPackage;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
	
	//checkbox
	private JCheckBox checkBoxAuto;

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
	private Vector<JLabel> labelVector;
	private Vector<String> labelNamesVector;
	private Vector<JButton> buttonVector;

	//IMPLEMENTATION
	//------------------------------------------------------------------------------------------------------------------------------------
	public GameInterfaceView()
	{
		//sets size of parent panel
		this.setPreferredSize(new Dimension(800,800));
		
		//initiate objects
		initiationOfObjects();
		setVectors();

		//sets interface elements in panel
		setButtons();
		setLabels();
		setCheckBox();
		setPanels();
		fillWindow();
	}
	
	//initiation of SWING objects in interface
	private void initiationOfObjects()
	{
		//initiation of containers
		labelMap = new HashMap<String,JLabel>();
		labelVector = new Vector<JLabel>();
		labelNamesVector = new Vector<String>();
		buttonVector = new Vector<JButton>();

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
		
		//initiation of buttons
		buttonStart = new JButton("START");
		buttonBack = new JButton("BACK");
		buttonWeaponMissiles = new JButton("MISSILES");
		buttonWeaponLaser = new JButton("LASER");
		buttonWeaponBlaster = new JButton("BLASTER");
		buttonWeaponBomb = new JButton("BOMB");
		
		//initiate checkBox
		checkBoxAuto = new JCheckBox("AUTO FIRE OFF");
		
		//initiate panels
		weaponsInfoPanel = new JPanel();
		weaponsPanel = new JPanel();
		statusPanel = new JPanel();
		menuPanel = new JPanel();
	}

	//sets vector containers for other objects (put these objects in containers just to shorten code later)
	public void setVectors()
	{
		//sets array with labes, names and buttons
		JButton buttonArray[] = {buttonStart, buttonBack, buttonWeaponMissiles, buttonWeaponBlaster, buttonWeaponLaser, buttonWeaponBomb};
		JLabel labelArray[] = {labelPoints, labelLevel, labelHP, labelShield, labelMissileNumber, labelMissilePower, labelMissileSpeed,
				labelBlasterNumber, labelBlasterPower, labelBlasterSpeed, labelLaserNumber, labelLaserPower, labelBombNumber, labelShieldMax};
		String labelNamesArray[] = {"labelPoints", "labelLevel", "labelHP", "labelShield", "labelMissileNumber", "labelMissilePower", "labelMissileSpeed",
				"labelBlasterNumber", "labelBlasterPower", "labelBlasterSpeed", "labelLaserNumber", "labelLaserPower", "labelBombNumber", "labelShieldMax"};

		//add objects from array to vectors
		for (JButton button : buttonArray){buttonVector.add(button);}
		for (JLabel label : labelArray){labelVector.add(label);}
		for (String labelName : labelNamesArray){labelNamesVector.add(labelName);}
	}

	private void setButtons()
	{
		//setting buttons in game interface
		defineButton(buttonStart, "START", new Dimension(120,40), false);
		defineButton(buttonBack, "BACK", new Dimension(120,40), false);
		defineButton(buttonWeaponMissiles, "MISSILES", new Dimension(120,80), false);
		defineButton(buttonWeaponLaser, "LASER", new Dimension(120,80), false);
		defineButton(buttonWeaponBlaster, "BLASTER", new Dimension(120,80), false);
		defineButton(buttonWeaponBomb , "BOMB", new Dimension(120,80), false);
	}

	//define buttons of game interface menu
	public void defineButton(JButton button, String name, Dimension size, boolean makeFocusable)
	{
		button.setName(name);
		button.setPreferredSize(size);
		button.setFocusable(makeFocusable);
	}
	
	private void setCheckBox()
	{
		checkBoxAuto.setFocusable(false);
	}

	//sets labels of game interface menu (info texts)
	private void setLabels()
	{
		for (int i=0; i<labelVector.size(); ++i)
		{
			defineLabels(labelVector.get(i), labelNamesVector.get(i));
		}
	}

	private void defineLabels(JLabel label, String mapKey)
	{
		labelMap.put(mapKey, label);
	}

	//updating labels texts, checked every trigger of Timer in controller
	public void updateLabels(String name, String text, int value)
	{
		labelMap.get(name).setText(text+ " "+ new Integer(value).toString());
	}

	//setting sup-panels of this panel
	private void setPanels()
	{
		//setting panels and adding components to them

		//weapon info panel, define information and add labels to it
		weaponsInfoPanel.setPreferredSize(new Dimension(120, 250));
		weaponsInfoPanel.setBackground(Color.red);
		weaponsInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		for (int i=4; i<labelVector.size(); ++i) {weaponsInfoPanel.add(labelVector.get(i));}

		//weapons panel, contains buttons with possible choice of weapons and info about them
		weaponsPanel.setPreferredSize(new Dimension (130,650));
		weaponsPanel.setBackground(Color.red);
		for (int i=2; i<buttonVector.size(); ++i){weaponsPanel.add(buttonVector.get(i));}
		weaponsPanel.add(weaponsInfoPanel);
		weaponsPanel.add(checkBoxAuto);

		//status panel, contains labels to show information about player 
		statusPanel.setPreferredSize(new Dimension(630, 100));
		statusPanel.setBackground(Color.green);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
		for (int i=0; i<4; ++i){statusPanel.add(labelVector.get(i), BorderLayout.EAST);}

		//menu panel, contains button start and back
		menuPanel.setPreferredSize(new Dimension(130, 100));
		menuPanel.setBackground(Color.yellow);
		menuPanel.add(buttonStart);
		menuPanel.add(buttonBack);

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
		for (JButton button :buttonVector)
		{
			button.addActionListener(actionListener);
		}
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
	
	public boolean getIfCheckboxChecked()
	{
		return checkBoxAuto.isSelected();
	}
}
