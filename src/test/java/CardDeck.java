import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final Deque<Card> deck;

    public CardDeck() {
        this.deck = new ArrayDeque<>(createCards());
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Pattern pattern : Pattern.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(pattern, cardNumber));
            }
        }
        return cards;
    }

    public Deque<Card> getDeck() {
        return deck;
    }

    public Card drawCard() {
        return deck.pop();
    }

    public List<Card> drawCardWhenStart() {
        List<Card> cards = new ArrayList<>();
        cards.add(drawCard());
        cards.add(drawCard());
        return cards;
    }
}
