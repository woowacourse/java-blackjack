package domain.constant;

public enum CardType {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
