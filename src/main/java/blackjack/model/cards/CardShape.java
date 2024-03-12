package blackjack.model.cards;

public enum CardShape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String text;

    CardShape(String text) {
        this.text = text;
    }

    public static CardShape generate(int index) {
        return values()[index];
    }

    public String getText() {
        return text;
    }
}
