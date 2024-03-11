package blackjack.dto;

import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Name;
import java.util.LinkedHashMap;
import java.util.Map;

public record PlayerGameResultsDto(Map<String, GameResult> resultMap) {

    public static PlayerGameResultsDto fromPlayerGameResults(Map<Name, GameResult> playerGameResults) {
        Map<String, GameResult> gameResult = new LinkedHashMap<>();

        for (Name playerName : playerGameResults.keySet()) {
            gameResult.put(playerName.value(), playerGameResults.get(playerName));
        }

        return new PlayerGameResultsDto(gameResult);
    }
}
