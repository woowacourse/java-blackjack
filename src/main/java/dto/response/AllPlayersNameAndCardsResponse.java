package dto.response;

import dto.PlayedGameResult;
import java.util.List;

public record AllPlayersNameAndCardsResponse(List<NameAndCardsResponse> allInfos) {

    public static AllPlayersNameAndCardsResponse from(List<PlayedGameResult> players) {
        return new AllPlayersNameAndCardsResponse(responses(players));
    }

    private static List<NameAndCardsResponse> responses(List<PlayedGameResult> players) {
        return players.stream()
                .map(NameAndCardsResponse::from)
                .toList();
    }
}
