import controller.BlackjackController;
import java.util.List;
import model.Card;
import model.Deck;
import model.DeckFactory;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        List<Card> cards = DeckFactory.initializeDeck();
        Deck deck = new Deck(cards);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        final BlackjackController blackjackController = new BlackjackController(deck, inputView, outputView);
        blackjackController.start();

    }
}
