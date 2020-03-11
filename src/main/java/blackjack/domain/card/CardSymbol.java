package blackjack.domain.card;

public enum CardSymbol {

    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int cardNumber;
    private final String cardSymbol;

    CardSymbol(int cardNumber, String cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

    public String getCardSymbol() {
        return cardSymbol;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public boolean isAce() {
        return this == ACE;
    }
}
