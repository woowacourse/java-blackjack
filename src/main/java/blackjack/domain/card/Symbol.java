package blackjack.domain.card;

/**
 * 카드의 점수에 대한 정보를 담고 있는 클래스 입니다
 */

public enum Symbol {
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

    private final int score;

    Symbol(final int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
