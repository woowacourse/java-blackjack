package blackJack.domain.Card;

public enum Suit {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private String shapeName;

    Suit(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }

}
