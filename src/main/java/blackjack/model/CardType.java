package blackjack.model;

public enum CardType {
    NORMAL_2(2),
    NORMAL_3(3),
    NORMAL_4(4),
    NORMAL_5(5),
    NORMAL_6(6),
    NORMAL_7(7),
    NORMAL_8(8),
    NORMAL_9(9),
    NORMAL_10(10),
    ACE(1),
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    private final int point;

    CardType(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
