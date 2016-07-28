package ControllerPackage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMainMenuListener implements ActionListener 
{
	
	public GameMainMenuListener(){}
	
	//triggered on button click in main menu
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//starts game after START GAME clicked
		if (((Component) e.getSource()).getName() == "START GAME")
		{
			GameController.getGameView().startEndGameSession();
		}
		//exits after QUIT clicked
		else if (((Component) e.getSource()).getName() == "QUIT")
		{
			System.exit(0);
		}
	}
}
