package blackjack.dto.card;

import java.util.Arrays;

import blackjack.domain.card.CardNumber;

public enum CardNumberDto {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    private final String displayName;

    CardNumberDto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CardNumberDto from(CardNumber cardNumber) {
        return Arrays.stream(values())
            .filter(cardNumberDto -> cardNumberDto.name().equals(cardNumber.name()))
            .findAny()
            .orElse(null);
    }
}
