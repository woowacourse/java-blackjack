package blackjack.model;

public enum CardShape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String detail;

    CardShape(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
