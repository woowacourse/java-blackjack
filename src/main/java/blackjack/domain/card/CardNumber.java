package blackjack.domain.card;

public enum CardNumber {
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
    QUEEN("Q", 10),
    KING("K", 10),
    JACK("J", 10)
    ;

    private final String abbreviation;
    private final int number;

    CardNumber(String abbreviation, int number) {
        this.abbreviation = abbreviation;
        this.number = number;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getNumber() {
        return number;
    }
}
