package controller.dto;

import domain.Card;
import java.util.List;

public record InitialDealResponse(
        DealerInitialDealResult dealerInitialDealResult,
        List<PlayerInitialDealResult> playerInitialDealResults
) {

    public record DealerInitialDealResult(
            String name,
            Card card
    ) {
    }

    public record PlayerInitialDealResult(
            String name,
            List<Card> cards
    ) {
    }
}
