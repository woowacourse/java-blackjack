import controller.BlackjackGame;
import domain.card.CardDivider;

public class Application {
	public static void main(String[] args) {
		CardDivider cardDivider = new CardDivider();
		BlackjackGame blackjackGame = new BlackjackGame(cardDivider);
		blackjackGame.run();
	}
}
