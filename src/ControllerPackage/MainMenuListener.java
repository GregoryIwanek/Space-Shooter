package ControllerPackage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuListener implements ActionListener 
{
	
	public MainMenuListener(){}
	
	//triggered on button click in main menu
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//gets name of clicked button
		String clickedButton = ((Component) e.getSource()).getName();
		
		//starts game after START GAME button clicked
		if (clickedButton == "START GAME")
		{
			GameController.getGameView().startEndGameSession();
		}
		//exits after QUIT button clicked
		else if (clickedButton == "QUIT")
		{
			System.exit(0);
		}
	}
}
