import java.util.ArrayList;
import java.util.List;

public class TestDefaults {
    private static final List<Card> DEFAULT_CARDS = createCards();

    public static Deck createDeck(){
        return new Deck(createCards());
    }

    public static List<Card> createCards(){
        List<Card> deck = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }

        return deck;
    }

    public static Card getCard(int idx) {
        return DEFAULT_CARDS.get(idx);
    }
}
