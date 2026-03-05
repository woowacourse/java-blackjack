package vo;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }
}
