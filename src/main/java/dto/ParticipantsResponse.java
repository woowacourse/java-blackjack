package dto;

import domain.Blackjack;
import java.util.List;

public record ParticipantsResponse(DealerResponse dealerResponse, List<PlayerResponse> playerResponse) {
    public static ParticipantsResponse of(final Blackjack blackjack) {
        return new ParticipantsResponse(DealerResponse.of(blackjack.getDealer()),
                blackjack.getPlayers().stream().map(PlayerResponse::of).toList());
    }
}
