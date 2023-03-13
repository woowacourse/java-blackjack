package blackjack.domain.card;

public enum Suit {

    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String word;

    Suit(final String word) {
        this.word = word;
    }

    public String word() {
        return word;
    }
}
