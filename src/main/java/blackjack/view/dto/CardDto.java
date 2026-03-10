package blackjack.view.dto;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;

public record CardDto(
        Rank rank,
        Suit suit
) {
    public static CardDto from(Card card) {
        return new CardDto(card.rank(), card.suit());
    }
}
