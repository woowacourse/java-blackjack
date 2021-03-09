package blackjack.domain.card;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클럽"),
    DIAMOND("다이아몬드");

    private final String suit;

    Suit(final String symbol) {
        this.suit = symbol;
    }

    public String getSuit() {
        return suit;
    }
}
