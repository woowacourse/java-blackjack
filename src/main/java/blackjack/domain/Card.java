package blackjack.domain;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Letter letter;

    public Card(Symbol symbol, Letter letter) {
        this.symbol = symbol;
        this.letter = letter;
    }

    public boolean isACE() {
        return this.letter == Letter.ACE;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Letter getNumber() {
        return letter;
    }

    public int getNumberValue() {
        return letter.getValue();
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
        return symbol == card.symbol && letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, letter);
    }
}
