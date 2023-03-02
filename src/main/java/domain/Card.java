package domain;

public class Card {
    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public int getCardValue() {
        return cardNumber.getValue();
    }

    public String getCard(){
        return cardNumber.getName() + cardPattern.getPattern();
    }
}
