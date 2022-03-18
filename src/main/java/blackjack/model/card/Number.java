package blackjack.model.card;

public enum Number {

    ACE(1, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "JACK"),
    QUEEN(10, "QUEEN"),
    KING(10, "KING"),
    ;

    private final int value;
    private final String valueForPrint;

    Number(int value, String valueForPrint) {
        this.value = value;
        this.valueForPrint = valueForPrint;
    }
}
