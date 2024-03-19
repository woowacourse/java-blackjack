package blackjack.dto;

import blackjack.model.card.Card;
import java.util.List;

public record CardsOutcome(List<CardOutcome> cards) {
    public static CardsOutcome from(List<Card> cards) {
        return new CardsOutcome(cards.stream()
                .map(CardOutcome::from)
                .toList());
    }
}
