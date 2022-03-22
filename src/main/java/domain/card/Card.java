package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class Card {

    private static final HashMap<String, Card> CACHE = new HashMap<>();

    private final Symbol symbol;
    private final Denomination denomination;

    private Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    static {
        for (Symbol symbol : Symbol.values()) {
            addCard(symbol);
        }
    }

    private static void addCard(Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            CACHE.put(symbol.getLetter() + denomination.getLetter(),
                new Card(symbol, denomination));
        }
    }

    public static Card of(Symbol symbol, Denomination denomination) {
        return CACHE.get(symbol.getLetter() + denomination.getLetter());
    }

    public static List<Card> getShuffledCardCache() {
        List<Card> cards = new ArrayList<>(CACHE.values());
        Collections.shuffle(cards);
        return cards;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getDenomination() {
        return denomination.getLetter();
    }

    public String getSymbol() {
        return symbol.getLetter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Card card = (Card) o;

        if (symbol != card.symbol) {
            return false;
        }
        return denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}
