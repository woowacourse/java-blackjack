package blackjack.domain.card;

public enum Shape {
    SPADE("스페이드"), HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버");

    final String description;

    Shape(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
