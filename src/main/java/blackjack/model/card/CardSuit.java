package blackjack.model.card;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
