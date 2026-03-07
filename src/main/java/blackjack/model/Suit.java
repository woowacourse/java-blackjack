package blackjack.model;

public enum Suit {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트");

    private final String korean;

    Suit(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
