package view.gamer;

import domain.card.CardType;

enum ViewCardType {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String output;

    ViewCardType(String output) {
        this.output = output;
    }

    static ViewCardType of(CardType cardType) {
        return valueOf(cardType.name());
    }

    public String getOutput() {
        return output;
    }
}
