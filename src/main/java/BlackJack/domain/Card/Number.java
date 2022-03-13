package BlackJack.domain.Card;

public enum Number {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String denomination;
    private final int value;

    Number(String denomination, int value) {
        this.denomination = denomination;
        this.value = value;
    }

    public String getDenomination() {
        return denomination;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce(){
        return "A".equals(denomination);
    }
}
