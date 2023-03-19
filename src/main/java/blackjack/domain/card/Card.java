package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardSymbol symbol;

    public Card(final CardNumber CardNumber, final CardSymbol symbol) {
        this.cardNumber = CardNumber;
        this.symbol = symbol;
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public String getSymbol() {
        return this.symbol.getSymbol();
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, symbol);
    }
}
