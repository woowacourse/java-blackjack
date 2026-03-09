package blackjack.domain;

public enum Suit {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;
    
    private final String displayName;
    
    Suit(String displayName) {
        this.displayName = displayName;
    }
    
    public String getSuitDisplayName() {
        return displayName;
    }
}
