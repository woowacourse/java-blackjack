package domain;

public enum CardShape {
    DIA("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String displayName;

    CardShape(String displayName) {
        this.displayName = displayName;
    }

    public static String shapeToText(CardShape cardShape) {
            return cardShape.displayName;
    }
}
