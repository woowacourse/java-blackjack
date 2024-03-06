import controller.BlackjackController;
import domain.Deck;
import domain.Gamer;
import domain.HoldingCards;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gamer dealer = new Gamer("딜러", HoldingCards.of());
        Gamer player = new Gamer("aaa", HoldingCards.of());
        BlackjackController blackjackController = new BlackjackController(dealer, List.of(player));
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
