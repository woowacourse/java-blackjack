package domain.card;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public int getScore() {
        return cardNumber.getCardNumber();
    }

    public boolean isAce() {
        return cardNumber.isAce();
    }

    public String getCardName() {
        return cardNumber.number + cardPattern.name;
    }
}
