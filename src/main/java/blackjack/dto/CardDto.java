package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public record CardDto(String suit, String denomination) {

    public static CardDto from(Card card) {
        final Suit suit = card.getSuit();
        final Denomination denomination = card.getDenomination();

        return new CardDto(suit.getExpression(), denomination.getExpression());
    }
}
