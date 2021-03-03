package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
    private static final List<Card> CARDS = new ArrayList<>();

    private final Symbol symbol;
    private final Number number;

    static {
        for (Symbol symbol : Symbol.values()) {
            for (Number number : Number.values()) {
                CARDS.add(new Card(symbol, number));
            }
        }
    }

    private Card(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card of(String symbolName, String numberName) {
        Symbol symbol = Symbol.from(symbolName);
        Number number = Number.from(numberName);

        Card findCard = CARDS.stream()
                .filter(card -> card.symbol == symbol && card.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 카드임!"));
        return findCard;
    }

    public int getScore() {
        return number.getScore();
    }

    public boolean containAce() {
        return number.isAce();
    }

    public String getCardStatus() {
        return number.getName() + symbol.name();
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
