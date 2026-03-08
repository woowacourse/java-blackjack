package domain.card;

public enum Suit {
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    ;

    private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
