package blackjack.domain.card;

public class Card {
    private final CardNumber cardNumber;
    private final CardType cardType;

    public Card(final CardNumber cardNumber, final CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public boolean isAce() {
        return (this.cardNumber == CardNumber.ACE);
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public CardType getCardType() {
        return this.cardType;
    }
}
