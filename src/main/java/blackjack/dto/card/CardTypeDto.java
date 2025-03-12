package blackjack.dto.card;

import java.util.Arrays;

import blackjack.domain.card.CardType;

public enum CardTypeDto {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    ;

    private final String displayName;

    CardTypeDto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CardTypeDto from(CardType cardType) {
        return Arrays.stream(values())
            .filter(cardTypeDto -> cardTypeDto.name().equals(cardType.name()))
            .findAny()
            .orElse(null);
    }
}
