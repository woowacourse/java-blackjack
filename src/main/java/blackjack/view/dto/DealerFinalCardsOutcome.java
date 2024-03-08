package blackjack.view.dto;

import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import java.util.List;

public record DealerFinalCardsOutcome(List<Card> cards, int totalScore) {
    public static DealerFinalCardsOutcome of(final Dealer dealer) {
        return new DealerFinalCardsOutcome(dealer.getCards(), dealer.calculateCardsTotal());
    }
}
