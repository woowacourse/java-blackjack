package blackjack.domain.card;

public abstract class Card {

    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public abstract String getCardPattern();

    public int getCardNumber() {
        return cardNumber.getValue();
    }
}
