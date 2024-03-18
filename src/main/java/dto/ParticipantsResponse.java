package dto;

import domain.player.Dealer;
import domain.player.Players;
import java.util.List;

public record ParticipantsResponse(DealerResponse dealerResponse, List<PlayerResponse> playerResponse) {
    public static ParticipantsResponse of(final Dealer dealer, final Players players) {
        return new ParticipantsResponse(DealerResponse.of(dealer),
                players.stream().map(PlayerResponse::of).toList());
    }
}
