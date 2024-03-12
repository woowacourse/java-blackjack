import controller.BlackjackController;
import domain.card.Deck;

public class Main {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
