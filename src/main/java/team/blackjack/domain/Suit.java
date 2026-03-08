package team.blackjack.domain;

public enum Suit {
    HEARTS("하트", true),
    DIAMONDS("다이아", true),
    CLUBS("클로버", false),
    SPADES("스페이드", false);

    private final String koreanName;
    private final boolean isRed;

    Suit(String koreanName, boolean isRed) {
        this.koreanName = koreanName;
        this.isRed = isRed;
    }

    public String getKoreanName(){
        return koreanName;
    }

    public boolean isRed() {
        return isRed;
    }
}
