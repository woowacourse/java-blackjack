package controller.dto;

import domain.Card;
import java.util.List;

public record InitialDealResponse(
        DealerDealResult dealerDealResult,
        List<PlayerDealResult> playerInitialDealResults
) {

    public record DealerDealResult(
            String name,
            Card card
    ) {
    }

    public record PlayerDealResult(
            String name,
            List<Card> cards
    ) {
    }
}
