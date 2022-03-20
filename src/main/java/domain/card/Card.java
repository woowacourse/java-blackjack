package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Card {

    private static final String NOT_FOUND_CARD = "해당하는 카드가 존재하지 않습니다.";
    private static final List<Card> CACHE = new ArrayList<>();

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
            .forEach(denomination -> CACHE.add(new Card(symbol, denomination)));
    }

    public boolean isAceCard() {
        return denomination == Denomination.ACE;
    }

    public static List<Card> values() {
        return List.copyOf(CACHE);
    }

    public static Card valueOf(Symbol symbol, Denomination denomination) {
        return CACHE.stream()
            .filter(card -> card.symbol == symbol && card.denomination == denomination)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CARD));
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
