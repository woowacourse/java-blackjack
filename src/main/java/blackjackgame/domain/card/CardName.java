package blackjackgame.domain.card;

public enum CardName {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K");

    private final int cardNumber;
    private final String cardName;

    CardName(int cardNumber, String cardName) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public boolean isCardNumberSame(int cardNumber) {
        return this.cardNumber == cardNumber;
    }
}
