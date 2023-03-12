package blackjack.domain.card;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String value;

    CardSuit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
