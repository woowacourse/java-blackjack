import controller.BlackjackGame;
import domain.card.CardDeck;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		try {
			BlackjackGame blackjackGame = new BlackjackGame();
			blackjackGame.play();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
		}
	}
}
