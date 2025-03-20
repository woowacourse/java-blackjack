package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DeckGenerator {
    public static Deck generateRandomDeck() {
        List<Card> deck = generateStandardCards();
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private static List<Card> generateStandardCards() {
        List<Card> deck = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }
}
