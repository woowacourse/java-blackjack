package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;

public record CardDto(Rank rank, Suit suit) {

    public static CardDto from(Card card) {
        return new CardDto(card.getRank(), card.getSuit());
    }
}
