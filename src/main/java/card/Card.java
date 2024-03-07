package card;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public int getCardNumber() {
        return cardNumber.scores.get(0);
    }

    public boolean isSameCardNumber(CardNumber checkNumber) {
        return cardNumber == checkNumber;
    }

    public String getCard() {
        return cardNumber.number + cardPattern.name;
    }
}
