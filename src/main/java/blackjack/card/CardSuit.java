package blackjack.card;

public enum CardSuit {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클럽");

    private final String description;

    CardSuit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
