package blackjack.dto;

import java.util.ArrayList;
import java.util.List;

public record PlayersBettingRequest(
    List<PlayerBettingRequest> value
) {

    public static PlayersBettingRequest from(List<PlayerBettingRequest> playerBettingRequests) {
        return new PlayersBettingRequest(playerBettingRequests);
    }

    public static PlayersBettingRequest createInitialRequest(List<String> playerNames) {
        List<PlayerBettingRequest> result = new ArrayList<>();
        for (String playerName : playerNames) {
            result.add(PlayerBettingRequest.createInitialRequest(playerName));
        }
        return new PlayersBettingRequest(result);
    }
}
