import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private static final int DRAW_COUNT_WHEN_START = 2;

    private final List<Card> deck;

    public static CardDeck createCards() {
        List<Card> cards = new ArrayList<>();
        for (Pattern pattern : Pattern.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(pattern, cardNumber));
            }
        }
        return new CardDeck(cards);
    }

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> shuffle(CardShuffler cardShuffler) {
        return cardShuffler.shuffle(this.deck);
    }

    public List<Card> drawCardWhenStart() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < DRAW_COUNT_WHEN_START; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    public Card drawCard() {
        return deck.removeLast();
    }

    public List<Card> getDeck() {
        return deck;
    }
}
