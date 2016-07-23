package GamePackage;
import ControllerPackage.*;
import ModelPackage.*;
import ViewPackage.GameView;

public class GameSpaceShooter 
{
	public static GameView gameView = new GameView();
	public static GameModel gameModel = new GameModel();

	public static void main (String arg[])
	{
		GameController gameController = new GameController(gameView, gameModel);
		gameView.setVisible(true);
	}
}
