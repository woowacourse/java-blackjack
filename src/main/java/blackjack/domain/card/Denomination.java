package blackjack.domain.card;

import static blackjack.domain.state.BlackJack.BLACKJACK_NUMBER;

public enum Denomination {
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

    private static final int REMAIN_ACE_COUNT = 10;

    private final int score;
    private final String name;

    Denomination(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static int plusRemainAceScore(int score, long aceCount) {
        for (int i = 0; i < aceCount; i++) {
            score = plusRemainAceScore(score);
        }
        return score;
    }

    private static int plusRemainAceScore(int score) {
        if (score + REMAIN_ACE_COUNT <= BLACKJACK_NUMBER) {
            score += REMAIN_ACE_COUNT;
        }
        return score;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int addScore(int score) {
        return this.score + score;
    }

    public String getName() {
        return this.name;
    }
}
