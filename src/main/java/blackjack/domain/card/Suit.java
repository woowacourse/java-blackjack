package blackjack.domain.card;

public enum Suit {
    CLUB("클럽"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
