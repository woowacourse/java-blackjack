package domain.card;


import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final CardSuitSymbol cardSuitSymbol;

    public Card(CardNumber cardNumber, CardSuitSymbol cardSuitSymbol) {
        this.cardNumber = cardNumber;
        this.cardSuitSymbol = cardSuitSymbol;
    }

    public boolean isAce() {
        return this.cardNumber.equals(CardNumber.ACE);
    }


    public int getCardNumber() {
        return this.cardNumber.getCardNumber();
    }

    @Override
    public String toString() {
        return getCardNumber() + this.cardSuitSymbol.getSuitSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardNumber == card.cardNumber &&
                cardSuitSymbol == card.cardSuitSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardSuitSymbol);
    }
}
