package GamePackage;
import ControllerPackage.*;
import ModelPackage.*;
import ViewPackage.GameView;

public class GameSpaceShooter 
{
	//sets main game view section and main game model section
	public static GameView gameView = new GameView();
	public static GameModel gameModel = new GameModel();

	public static void main (String arg[])
	{
		//sets controller of the view and model sections
		GameController gameController = new GameController(gameView, gameModel);
		gameView.setVisible(true);
	}
}
