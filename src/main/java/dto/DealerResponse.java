package dto;

import domain.player.Dealer;
import java.util.List;

public record DealerResponse(List<CardResponse> cardsResponse, Integer score) {
    public static DealerResponse of(final Dealer dealer) {
        return new DealerResponse(dealer.getHands().stream().map(CardResponse::of).toList(), dealer.getScore());
    }
}
