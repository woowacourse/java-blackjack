package blackJack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> CARDS = new ArrayList<>();
    
    static {
        for (Symbol value : Symbol.values()) {
            createSymbolCards(value);
        }
    }

    private final Symbol symbol;
    private final Denomination denomination;

    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    private static void createSymbolCards(Symbol value) {
        for (Denomination denomination : Denomination.values()) {
            CARDS.add(new Card(value, denomination));
        }
    }

    public boolean isSameDenominationAsAce() {
        return denomination == Denomination.ACE;
    }

    public static List<Card> createNewCards() {
        return new ArrayList<>(CARDS);
    }

    public String getSymbolName() {
        return symbol.getName();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getDenominationName() {
        return denomination.getDenomination();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card)o;
        return symbol == card.symbol && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, denomination);
    }
}
