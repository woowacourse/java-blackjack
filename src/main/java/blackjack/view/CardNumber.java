package blackjack.view;

public enum CardNumber {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    final String name;

    CardNumber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
