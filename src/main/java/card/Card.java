package card;

public class Card {

    private static final int FIRST_POSITION_INDEX = 0;

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public int getDefaultCardScore() {
        return cardNumber.getCardNumber(FIRST_POSITION_INDEX);
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }

    public String getCardHand() {
        return cardNumber.number + cardPattern.name;
    }
}
