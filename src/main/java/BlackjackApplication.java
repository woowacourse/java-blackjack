import controller.BlackjackController;
import java.util.List;
import model.BlackjackService;
import model.card.Card;
import model.card.CardFactory;
import model.card.CardShuffler;
import model.card.Deck;
import model.card.SimpleCardShuffler;

public class BlackjackApplication {

    public static void main(String[] args) {
        CardShuffler cardShuffler = new SimpleCardShuffler();
        List<Card> fullCards = CardFactory.createFullCards();
        List<Card> shuffledCards = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCards);
        BlackjackService blackjackService = new BlackjackService(deck);

        BlackjackController controller = new BlackjackController(blackjackService);
        controller.run();
    }
}
