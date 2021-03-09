package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private Symbol symbol;
    private CardNumber cardNumber;

    public Card(Symbol symbol, CardNumber cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return cardNumber.getName() + symbol.getName();
    }

    public int getAccumulateScore(int accumulatedScore) {
        return cardNumber.getScore(accumulatedScore);
    }

    public int getAccumulateScore() {
        return cardNumber.getScore();
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
        return symbol == card.symbol && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, cardNumber);
    }

    @Override
    public String toString() {
        return cardNumber.getName() + symbol.getName();
    }

}
