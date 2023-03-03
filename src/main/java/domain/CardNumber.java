package domain;

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
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String numberToPrint;
    private final int score;

    CardNumber(String numberToPrint, int score) {
        this.numberToPrint = numberToPrint;
        this.score = score;
    }

    public String getNumberToPrint() {
        return numberToPrint;
    }

    public int getScore(){
        return score;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }
}
