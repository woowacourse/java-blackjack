import controller.BlackjackGame;
import domain.card.CardDivider;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		try {
			CardDivider cardDivider = new CardDivider();
			BlackjackGame blackjackGame = new BlackjackGame(cardDivider);
			blackjackGame.run();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
		}
	}
}
