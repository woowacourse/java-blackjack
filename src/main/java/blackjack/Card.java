package blackjack;

import java.util.Objects;

public class Card {

    private final String symbol;
    private final int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
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
        return number == card.number &&
            Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }

    public String getName() {
        return number + symbol;
    }

    public int getScore() {
        return number;
    }
}
