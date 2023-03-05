package domain.card;


//TODO: 도메인이 뷰에 의존하지 않도록 변경 & Ace 값 추가
public enum CardValue {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(11, "A");

    private final int score;
    private final String name;

    CardValue(final int score, final String name) {
        this.score = score;
        this.name = name;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
