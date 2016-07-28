package ControllerPackage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterfaceListener implements ActionListener
{
	public GameInterfaceListener(){}

	//triggered on button click in game interface in game section
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//triggered after START clicked, starts timer and game
		if (((Component) e.getSource()).getName() == "START")
		{
			GameController.gameSessionListener.setTimer(true);
		}
		//triggered after BACK clicked, stops game and hides game section
		else if (((Component) e.getSource()).getName() == "BACK")
		{
			GameController.gameSessionListener.setTimer(false);
			GameController.getGameView().startEndGameSession();
		}
	}
}
