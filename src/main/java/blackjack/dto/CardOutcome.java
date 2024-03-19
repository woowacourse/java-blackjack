package blackjack.dto;

import blackjack.model.card.Card;

public record CardOutcome(String card) {
    public static CardOutcome from(Card card) {
        return new CardOutcome(card.suit().getName() + card.denomination().getName());
    }
}
