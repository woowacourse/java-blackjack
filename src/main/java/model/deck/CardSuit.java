package model.deck;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String shapeMeaning;

    CardSuit(String shapeMeaning) {
        this.shapeMeaning = shapeMeaning;
    }

    public String getShapeMeaning() {
        return shapeMeaning;
    }
}
