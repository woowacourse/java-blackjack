package blackjack.dto;

import blackjack.domain.trump.Card;
import blackjack.domain.trump.Denomination;
import blackjack.domain.trump.Suit;

public record CardDto(
    String denomination,
    String suit
) {

    public static CardDto from(final Card card) {
        final Suit suit = card.getSuit();
        final Denomination denomination = card.getDenomination();

        return new CardDto(denomination.getSymbol(), suit.getName());
    }
}