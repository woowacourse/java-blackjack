package domain;

public enum Rank {
    ACE("A", new Score(1)),
    TWO("2", new Score(2)),
    THREE("3", new Score(3)),
    FOUR("4", new Score(4)),
    FIVE("5", new Score(5)),
    SIX("6", new Score(6)),
    SEVEN("7", new Score(7)),
    EIGHT("8", new Score(8)),
    NINE("9", new Score(9)),
    TEN("10", new Score(10)),
    J("J", new Score(10)),
    Q("Q", new Score(10)),
    K("K", new Score(10));
    public static final Score BLACKJACK_MAX_NUMBER = new Score(21);
    public static final Score ACE_MAX_VALUE = new Score(11);
    public static final Score ACE_MIN_VALUE = new Score(1);

    private final String displayValue;
    private final Score score;

    Rank(String displayValue, Score scoreValue) {
        this.displayValue = displayValue;
        this.score = scoreValue;
    }

    public static Score totalSum(int aceAmount, Score sum) {
        for (int i = 1; i <= aceAmount; i++) {
            sum = sum.add(decideAceValue(sum, aceAmount - i));
        }
        return sum;
    }

    private static Score decideAceValue(Score sum, int leftAce) {
        if (sum.add(ACE_MAX_VALUE).isLessThanOrEqualTo(BLACKJACK_MAX_NUMBER)
                && BLACKJACK_MAX_NUMBER.sub(sum.add(ACE_MAX_VALUE)).isGreaterThanOrEqualTo(leftAce)) {
            return ACE_MAX_VALUE;
        }
        return ACE_MIN_VALUE;
    }


    public boolean isAce() {
        return this == Rank.ACE;
    }

    public Score getScore() {
        return score;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
