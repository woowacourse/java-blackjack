package blackjack.domain.card;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card {
    private static final Map<String, Card> CARDS;
    public static final int CARDS_CAPACITY = 52;

    private final Suits suit;
    private final Denominations denomination;

    static {
        Map<String, Card> cardValues = new HashMap<>(CARDS_CAPACITY);

        for (Suits suits : Suits.values()) {
            assembleWithDenominations(cardValues, suits);
        }

        CARDS = Collections.unmodifiableMap(cardValues);
    }

    private static void assembleWithDenominations(Map<String, Card> cardValues, Suits suits) {
        for (Denominations denominations : Denominations.values()) {
            final String key = denominations.getName() + suits.getName();
            cardValues.put(key, new Card(suits, denominations));
        }
    }

    private Card(Suits suit, Denominations denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card from(Suits suit, Denominations denomination) {
        Card card = CARDS.get(denomination.getName() + suit.getName());
        if (card == null) {
            throw new IllegalArgumentException();
        }
        return card;
    }

    public static Card from(Denominations denomination, Suits suit) {
        Card card = CARDS.get(denomination.getName() + suit.getName());
        if (card == null) {
            throw new IllegalArgumentException();
        }
        return card;
    }


    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination == Denominations.ACE;
    }

    public String getCardName() {
        return denomination.getName() + suit.getName();
    }

}
