package blackjack.card;

import blackjack.player.Score;

public enum Rank {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final Score score;

    Rank(int score) {
        this.score = new Score(score);
    }

    Score getScore() {
        return this.score;
    }
}
