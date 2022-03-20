package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private final static Map<Key, Card> cache = new HashMap<>(52);

    private final Key key;

    private Card(final Key key) {
        this.key = key;
    }

    public static Card of(final CardType cardType, final CardValue cardValue) {
        return cache.computeIfAbsent(Key.of(cardType, cardValue), ignored -> new Card(Key.of(cardType, cardValue)));
    }

    public boolean isAce() {
        return key.cardValue.isAce();
    }

    @Override
    public String toString() {
        return String.format("%s%s", key.cardType.getName(), key.cardValue.getName());
    }

    public int getValue() {
        return key.cardValue.getValue();
    }

    static class Key {
        private final CardType cardType;
        private final CardValue cardValue;

        private Key(final CardType cardType, final CardValue cardValue) {
            this.cardType = cardType;
            this.cardValue = cardValue;
        }

        public static Key of(final CardType cardType, final CardValue cardValue) {
            return new Key(cardType, cardValue);
        }
    }
}
