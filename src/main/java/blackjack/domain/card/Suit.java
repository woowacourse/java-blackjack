package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String suitName;

    Suit(final String suitName) {
        this.suitName = suitName;
    }

    public String getName() {
        return suitName;
    }
}
