package blackjack.dto;

import blackjack.model.dealer.Dealer;
import blackjack.vo.Card;
import java.util.List;

public record DealerFinalCardsOutcome(List<Card> cards, int score) {
    public static DealerFinalCardsOutcome from(final Dealer dealer) {
        return new DealerFinalCardsOutcome(dealer.getCards(), dealer.calculateCardsScore());
    }
}
