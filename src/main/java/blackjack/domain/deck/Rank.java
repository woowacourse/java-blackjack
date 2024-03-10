package blackjack.domain.deck;

public enum Rank {

    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), KING(10), QUEEN(10);

    private final int score;

    Rank(int score) {
        this.score = score;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public boolean isSameName(String name) {
        return this.name().equals(name);
    }

    public int getScore(int totalScore) { // TODO 시소랑 토의
        if (this == ACE && totalScore + 11 <= 21) {
            return 11;
        }
        return score;
    }
}
