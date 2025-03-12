package domain.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckGenerator {
    public static Deck generateRandomDeck() {
        List<Card> deck = generateStandardCards();
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public static Deck generateTestDeck(List<Card> cards) {
        return new Deck(cards);
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
