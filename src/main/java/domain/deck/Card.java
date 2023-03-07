package domain.deck;

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

    public String getCardName() {
        return cardNumber.getName();
    }

    public String getCardPattern() {
        return cardPattern.getPattern();
    }
}
