package domain;

public enum CardShape {
    DIA("다이아몬드"),
    HEART("스페이드"),
    SPADE("클로버"),
    CLOVER("하트");

    private final String displayName;

    CardShape(String displayName) {
        this.displayName = displayName;
    }

    public static String shapeToText(CardShape cardShape) {
            return cardShape.displayName;
    }
}
