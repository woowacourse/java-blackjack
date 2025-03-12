package blackjack.card;

public enum Suit {
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String text;

    Suit(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
