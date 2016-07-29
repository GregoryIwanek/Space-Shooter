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
		//get name of clicked button ( names defined in view section)
		String clickedButton = ((Component) e.getSource()).getName();
		
		switch (clickedButton) 
		{
		case "START":
			//triggered after START clicked, starts timer and game
			GameController.gameSessionListener.setTimer(true);
			break;
		case "BACK":
			//triggered after BACK clicked, stops game and hides game section
			GameController.gameSessionListener.setTimer(false);
			GameController.getGameView().startEndGameSession();
			break;
		case "MISSILES":
			setTypeOfWeaponForPlayer("MISSILES");
			break;
		case "BLASTER":
			setTypeOfWeaponForPlayer("BLASTER");
			break;
		case "BOMB":
			setTypeOfWeaponForPlayer("BOMB");
			break;
		case "LASER":
			setTypeOfWeaponForPlayer("LASER");
			break;
		default:
			setTypeOfWeaponForPlayer("BLASTER");
			break;
		}
	}
	
	//sets type of weapon depending on clicked button with weapons
	public void setTypeOfWeaponForPlayer(String typeOfWeapon)
	{
		GameController.getGameModel().getPlayerModel().setTypeOfWeapon(GameController.getGameModel().getPlayer(), typeOfWeapon);
	}
}
