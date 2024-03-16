package blackjack.model.dealer;

import blackjack.model.card.Card;
import java.util.List;

public record DealerFinalCardsOutcome(List<Card> cards, int totalScore) {
    public static DealerFinalCardsOutcome from(final Dealer dealer) {
        return new DealerFinalCardsOutcome(dealer.getCards(), dealer.calculateCardsTotalScore());
    }
}
