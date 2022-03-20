package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
    
    private static final List<Card> CACHE = new ArrayList<>();

    private final Denomination denomination;
    private final Symbol symbol;

    static {
        for (Denomination denomination : Denomination.values()) {
            Arrays.stream(Symbol.values())
                .forEach(symbol -> CACHE.add(new Card(denomination, symbol)));
        }
    }

    private Card(final Denomination denomination, final Symbol symbol) {
        this.denomination = denomination;
        this.symbol = symbol;
    }

    public static Card of(final Denomination inputDenomination, final Symbol inputSymbol) {
        return CACHE.stream()
            .filter(card -> card.getDenomination().equals(inputDenomination))
            .filter(card -> card.getSymbol().equals(inputSymbol))
            .findFirst()
            .orElse(new Card(inputDenomination, inputSymbol));
    }

    public int point() {
        return denomination.getPoint();
    }

    public boolean isAce() {
        return denomination.equals(Denomination.ACE);
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
