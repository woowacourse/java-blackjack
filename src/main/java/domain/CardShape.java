package domain;

public enum CardShape {
    DIA(1),
    HEART(2),
    SPADE(3),
    CLOVER(4),
    ;

    private final int index;

    CardShape(int index) {
        this.index = index;
    }

    public static CardShape pickCardShape(int index) {
        for (CardShape value : values()) {
            if (value.index == index) {
                return value;
            }
        }
        throw new IllegalArgumentException("카드 모양 범위 내에서 선택해주세요 (1~4)");
    }
}
