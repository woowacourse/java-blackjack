package blackjack.dto;

import java.util.List;

public record PlayersBettingRequest(
    List<PlayerBettingRequest> value
) {

    public static PlayersBettingRequest from(List<PlayerBettingRequest> playerBettingRequests) {
        return new PlayersBettingRequest(playerBettingRequests);
    }
}
