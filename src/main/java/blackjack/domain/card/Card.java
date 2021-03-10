package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
    private static final List<Card> CARDS = new ArrayList<>();

    static {
        for (Symbol symbol : Symbol.values()) {
            for (Number number : Number.values()) {
                CARDS.add(new Card(symbol, number));
            }
        }
    }

    private final Symbol symbol;
    private final Number number;

    private Card(final Symbol symbol, final Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card of(final String symbolName, final String numberName) {
        Symbol symbol = Symbol.from(symbolName);
        Number number = Number.from(numberName);

        Card findCard = CARDS.stream()
                .filter(card -> card.symbol == symbol && card.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 카드임!"));
        return findCard;
    }

    public int numberScore() {
        return number.getScore();
    }

    public String symbolName() {
        return symbol.getName();
    }

    public String numberName() {
        return number.getName();
    }

    public boolean containAce() {
        return number.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }
}
