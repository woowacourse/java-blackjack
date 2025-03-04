package blackjack.model;

public enum CardType {

    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String text;

    CardType(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
