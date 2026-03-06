package domain;

public enum Rank {
    ACE("A", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10), J("J", 10), Q("Q", 10), K("K", 10);
    private final String displayValue;
    private final int scoreValue;

    Rank(String displayValue, int scoreValue) {
        this.displayValue = displayValue;
        this.scoreValue = scoreValue;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    // ace를 제외한 합계가 15이다 -> 매개변수로 15를 주고, ace 넣을수있냐?
    public static int decideAceValue(int sum, int leftAce) {
        if (sum + 11 <= (21+1-leftAce)) {
            return 11;
        }
        return 1;
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }
}
