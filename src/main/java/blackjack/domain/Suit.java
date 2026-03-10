package blackjack.domain;

public enum Suit {
    SPADE("spade", "스페이드"),
    DIAMOND("diamond", "다이아몬드"),
    HEART("heart", "하트"),
    CLOVER("clover", "클로버"),
    ;

    private String name;
    private String displayName;

    Suit(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getSuitDisplayName() {
        return displayName;
    }
}
