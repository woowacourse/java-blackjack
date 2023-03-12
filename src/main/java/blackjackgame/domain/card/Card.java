package blackjackgame.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private final static Map<String, Card> cache = new HashMap<>(52);

    private final Symbol symbol;
    private final CardValue cardValue;

    private Card(final Symbol symbol, final CardValue cardValue) {
        this.symbol = symbol;
        this.cardValue = cardValue;
    }

    public static Card of(Symbol symbol, CardValue cardValue) {
        return cache.computeIfAbsent(toKey(symbol, cardValue), ignore -> new Card(symbol, cardValue));
    }

    private static String toKey(Symbol symbol, CardValue cardValue) {
        return symbol.getSymbol() + cardValue.getScore();
    }

    public String getSymbol() {
        return symbol.getSymbol();
    }

    public int getScore() {
        return cardValue.getScore();
    }

    public String getValue() {
        return cardValue.getValue();
    }

    public boolean isAce() {
        return cardValue.getValue().equals(CardValue.ACE.getValue());
    }
}
