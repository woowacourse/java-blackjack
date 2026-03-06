package constant;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("스페이드"),
    DIAMOND("다이아몬드"),
    ;

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
