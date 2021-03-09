package blackjack.domain.card;

public class Card {

    private final CardNumber cardNumber;
    private final CardType cardType;

    public Card(final CardNumber cardNumber, final CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

}
