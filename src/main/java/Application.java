import controller.BlackjackGame;
import domain.card.CardDeck;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		try {
			CardDeck cardDeck = new CardDeck();
			BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
			blackjackGame.run();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
		}
	}
}
