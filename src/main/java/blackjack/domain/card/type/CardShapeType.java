package blackjack.domain.card.type;

public enum CardShapeType {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로버");

    private final String shape;

    CardShapeType(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
