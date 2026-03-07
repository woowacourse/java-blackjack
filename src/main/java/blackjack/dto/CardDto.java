package blackjack.dto;

import blackjack.model.Card;
import blackjack.model.Rank;
import blackjack.model.Suit;

public record CardDto(
    Rank rank,
    Suit suit
) {
    public static CardDto from(Card card) {
        return new CardDto(card.rank(), card.suit());
    }
}
