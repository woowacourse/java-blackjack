package blackjack.domain.card;

public abstract class Card {

    private final CardNumber cardNumber;

    public Card(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public abstract String getCardPattern();

    public String getCardNumberType() {
        return cardNumber.getType();
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }
}
