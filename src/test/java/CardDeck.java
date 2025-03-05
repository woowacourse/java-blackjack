import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<Card> deck;

    public CardDeck() {
        this.deck = createCards();
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

    public List<Card> getDeck() {
        return deck;
    }

    public Card drawCard() {
        return deck.removeLast();
    }

    public List<Card> drawCardWhenStart() {
        List<Card> cards = new ArrayList<>();
        cards.add(drawCard());
        cards.add(drawCard());
        return cards;
    }
}
