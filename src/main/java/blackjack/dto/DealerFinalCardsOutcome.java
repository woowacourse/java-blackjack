package blackjack.dto;

import blackjack.model.dealer.Dealer;

public record DealerFinalCardsOutcome(CardsOutcome cards, int score) {
    public static DealerFinalCardsOutcome from(final Dealer dealer) {
        return new DealerFinalCardsOutcome(CardsOutcome.from(dealer.getCards()), dealer.calculateCardsScore());
    }
}
