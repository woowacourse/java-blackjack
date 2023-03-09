package type;

import domain.Score;

public enum Letter {

    TWO("2", Score.from(2)),
    THREE("3", Score.from(3)),
    FOUR("4", Score.from(4)),
    FIVE("5", Score.from(5)),
    SIX("6", Score.from(6)),
    SEVEN("7", Score.from(7)),
    EIGHT("8", Score.from(8)),
    NINE("9", Score.from(9)),
    TEN("10", Score.from(10)),
    ACE("A", Score.from(11)),
    KING("K", Score.from(10)),
    QUEEN("Q", Score.from(10)),
    JACK("J", Score.from(10));

    final String expression;
    final Score score;

    Letter(String expression, Score score) {
        this.expression = expression;
        this.score = score;
    }

    public String getExpression() {
        return expression;
    }

    public Score getScore() {
        return score;
    }

}
