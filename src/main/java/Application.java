import controller.BlackjackGame;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		try {
			BlackjackGame blackjackGame = new BlackjackGame();
			blackjackGame.run();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
		}
	}
}
