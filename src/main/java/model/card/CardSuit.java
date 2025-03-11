package model.card;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private String shapeMeaning;

    CardSuit(String shapeMeaning) {
        this.shapeMeaning = shapeMeaning;
    }

    public String getShapeMeaning() {
        return shapeMeaning;
    }
}
