package dto.response;

import dto.PlayedGameResult;
import java.util.List;

public record PlayerGameResultsResponse(List<PlayedGameResultResponse> results) {
    public static PlayerGameResultsResponse from(List<PlayedGameResult> playedGameResults) {
        return new PlayerGameResultsResponse(responses(playedGameResults));
    }

    private static List<PlayedGameResultResponse> responses(List<PlayedGameResult> players) {
        return players.stream()
                .map(PlayedGameResultResponse::from)
                .toList();
    }
}
