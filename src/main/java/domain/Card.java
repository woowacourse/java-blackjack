package domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    public int getCardValue() {
        return cardNumber.getValue();
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardPattern getCardPattern() {
        return cardPattern;
    }

}
