package blackjack.domain.Card;

public enum Shape {
    HEART("♥"),
    DIAMOND("♦"),
    SPADE("♠"),
    CLOVER("♣");

    private final String shapeName;

    Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }

}
