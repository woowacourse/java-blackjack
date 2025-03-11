package fixture;

import domain.Card;
import domain.Deck;
import domain.DeckGenerator;
import domain.Rank;
import domain.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {
    private static final int SUIT_COUNT = 4;

    private static final List<Card> deckFixture = new ArrayList<>();
    private static final Deck deck = DeckGenerator.generateTestDeck();

    static {
        for (int i = 0; i < 52; i++) {
            deckFixture.add(deck.drawNewCard());
        }
    }

    public static Card of(Rank rank, Suit suit) {
        int rankIndex = rank.ordinal();
        int suitIndex = suit.ordinal();
        return deckFixture.get(rankIndex * SUIT_COUNT + suitIndex);
    }
}
