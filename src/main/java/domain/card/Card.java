package domain.card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Card {

    private static final String NOT_FOUND_CARD = "해당하는 카드가 존재하지 않습니다.";
    private static final Map<String, Card> CACHE = new HashMap<>();

    private final Symbol symbol;
    private final Denomination denomination;

    static {
        Arrays.stream(Symbol.values())
            .forEach(Card::saveCard);
    }

    private Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    private static void saveCard(Symbol symbol) {
        Arrays.stream(Denomination.values())
            .forEach(denomination -> CACHE.put(makeMapKey(symbol, denomination), new Card(symbol, denomination)));
    }

    private static String makeMapKey(Symbol symbol, Denomination denomination) {
        return denomination.getLetter() + symbol.getLetter();
    }

    public boolean isAceCard() {
        return denomination == Denomination.ACE;
    }

    public static List<Card> values() {
        return List.copyOf(CACHE.values());
    }

    public static Card valueOf(Symbol symbol, Denomination denomination) {
        Card card = CACHE.get(makeMapKey(symbol, denomination));
        if (card == null) {
            throw new IllegalArgumentException(NOT_FOUND_CARD);
        }
        return card;
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
}
