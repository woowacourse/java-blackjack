package blackjack.domain.card;

public enum CardSuit {

    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    SPADE("스페이드");

    private final String description;

    CardSuit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
