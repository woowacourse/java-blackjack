package blackjack.domain.card;


import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final Symbol symbol;

    private Card(CardNumber cardNumber, Symbol symbol) {
        this.cardNumber = cardNumber;
        this.symbol = symbol;
    }

    public static Card of(CardNumber cardNumber, Symbol symbol) {
        return new Card(cardNumber, symbol);
    }

    public int score() {
        return cardNumber.score();
    }

    public String name() {
        return cardNumber.cardName() + symbol.getName();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
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
        return cardNumber == card.cardNumber && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, symbol);
    }
}
