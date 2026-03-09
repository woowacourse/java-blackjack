package domain.constant;

public enum CardMark {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    ;

    private final String description;

    CardMark(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
