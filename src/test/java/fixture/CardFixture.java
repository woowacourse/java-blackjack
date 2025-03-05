package fixture;

import domain.Card;
import domain.Deck;
import domain.Rank;
import domain.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {
    private static final int SUIT_COUNT = 4;
    public static List<Card> deckFixture = new ArrayList<>();
    private static final Deck deck = new Deck();

    static {
        for (int i = 0; i < 52; i++) {
            deckFixture.add(deck.random(new TestNumberGenerator()));
        }
    }

    public static Card of(Rank rank, Suit suit) {
        int rankIndex = rank.ordinal();
        int suitIndex = suit.ordinal();
        return deckFixture.get(rankIndex * SUIT_COUNT + suitIndex);
    }
}
