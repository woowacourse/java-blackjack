package domain.card;

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
        return trumpCardNumber.getNumber();
    }

    public String getTypeName() {
        return trumpCardType.getName();
    }

    public String getNumberSignature() {
        return trumpCardNumber.getSignature();
    }
}
