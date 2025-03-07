package blackjack.model.card;

public enum CardType {
    NORMAL_2(2, "2"),
    NORMAL_3(3, "3"),
    NORMAL_4(4, "4"),
    NORMAL_5(5, "5"),
    NORMAL_6(6, "6"),
    NORMAL_7(7, "7"),
    NORMAL_8(8, "8"),
    NORMAL_9(9, "9"),
    NORMAL_10(10, "10"),
    ACE(1, "A"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    ;

    private final int point;
    private final String detail;

    CardType(final int point, final String detail) {
        this.point = point;
        this.detail = detail;
    }

    public int getPoint() {
        return point;
    }

    public String getDetail() {
        return detail;
    }
}
