package blackjack.domain;

public enum NumberCandidate {
    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7),
    EIGHT("8", 8), NINE("9", 9), TEN("10", 10), J("J", 10), K("K", 10), Q("Q", 10), A("A", 11);

    private String number;
    private int value;

    NumberCandidate(String number, int value){
        this.number = number;
        this.value = value;
    }

    public String getNumber() {
        return this.number;
    }

    public int getValue() {
        return this.value;
    }
}
