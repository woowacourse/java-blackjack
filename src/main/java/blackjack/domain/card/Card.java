package blackjack.domain.card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Card {
    public static final int CARDS_CAPACITY = 52;
    private static Map<String, Card> cards;

    private final Suits suit;
    private final Denominations denomination;

    static {
        cards = new HashMap<>(CARDS_CAPACITY);
        Arrays.stream(Suits.values())
                .forEach(suit -> Arrays.stream(Denominations.values())
                        .forEach(denomination -> {
                            String key = denomination.getName() + suit.getName();
                            cards.put(key, new Card(suit, denomination));
                        }));
    }

    private Card(Suits suit, Denominations denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card from(String value) {
        Card card = cards.get(value);
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

    public String getName() {
        return denomination.getName() + suit.getName();
    }
}
