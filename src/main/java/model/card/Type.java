package model.card;

public enum Type {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    SPADE("스페이드");

    private String type;

    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
