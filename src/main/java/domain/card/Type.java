package domain.card;

public enum Type {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    private String typeString;

    Type(String type) {
        this.typeString = type;
    }

    public String getType() {
        return typeString;
    }
}
