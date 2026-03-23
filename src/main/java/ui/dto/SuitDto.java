package ui.dto;

import domain.card.Suit;

public enum SuitDto {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String value;

    SuitDto(String value) {
        this.value = value;
    }

    public static SuitDto toDto(Suit suit) {
        return SuitDto.valueOf(suit.name());
    }

    public String getValue() {
        return value;
    }
}