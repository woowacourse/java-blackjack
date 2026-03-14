package blackjack.dto;

import blackjack.domain.trump.Denomination;
import blackjack.domain.trump.Suit;

public record CardDto(
    String denomination,
    String suit
) {

    public static CardDto of(final Denomination denomination, final Suit suit) {
        return new CardDto(denomination.getSymbol(), suit.getName());
    }
}