package blackjack.view.dto;

import blackjack.model.dealer.Dealer;

public record DealerFinalCardsOutcome(String cards, int totalScore) {
    public static DealerFinalCardsOutcome of(final Dealer dealer) {
        return new DealerFinalCardsOutcome(
                dealer.getCards().toString(),
                dealer.calculateCardsTotalScore().getValue()
        );
    }
}
