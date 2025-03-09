import controller.BlackjackController;
import java.util.List;
import model.Card;
import model.Deck;
import model.DeckFactory;
import model.Judge;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        List<Card> cards = DeckFactory.initializeDeck();
        Deck deck = new Deck(cards);
        Judge judge = new Judge();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackController blackjackController = new BlackjackController(deck, judge, inputView, outputView);
        blackjackController.start();
    }
}
