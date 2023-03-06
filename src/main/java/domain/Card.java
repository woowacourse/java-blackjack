package domain;

public class Card {
    private final TrumpCardType trumpCardType;
    private final TrumpCardNumber trumpCardNumber;

    public Card(TrumpCardType trumpCardType, TrumpCardNumber trumpCardNumber) {
        this.trumpCardType = trumpCardType;
        this.trumpCardNumber = trumpCardNumber;
    }

    public boolean isAce() {
        return this.trumpCardNumber == TrumpCardNumber.ACE;
    }

    public int getScore() {
        return trumpCardNumber.getScore();
    }

    public String getShapeName() {
        return trumpCardType.getName();
    }

    public String getNumberName() {
        return trumpCardNumber.getSignature();
    }
}
