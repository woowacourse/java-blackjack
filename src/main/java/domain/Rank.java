package domain;

public enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), J(10), Q(10), K(10);
    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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
