package view.message;

import domain.card.Suit;

public enum SuitMessage {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String name;

    SuitMessage(String name) {
        this.name = name;
    }

    public static String of(Suit suit) {
        for (SuitMessage suitMessage : SuitMessage.values()) {
            if (suit.name().equals(suitMessage.name())) return suitMessage.getName();
        }

        throw new IllegalArgumentException("존재하지 않는 상징입니다.");
    }

    public String getName() {
        return name;
    }
}
