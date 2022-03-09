package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardSymbol symbol;
    private final CardNumber number;

    public Card(CardSymbol symbol, CardNumber number) {
        this.symbol = symbol;
        this.number = number;
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public int getNumberValue() {
        return number.getValue();
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardSymbol getSymbol() {
        return symbol;
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
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", number=" + number +
                '}';
    }
}
