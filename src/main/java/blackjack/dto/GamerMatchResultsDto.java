package blackjack.dto;

import blackjack.model.BlackJackGame;
import blackjack.model.MatchResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamerMatchResultsDto {
    private final Map<String, String> gamerMatchResults;

    public GamerMatchResultsDto(Map<String, MatchResult> results) {
        Map<String, String> matchResults = new LinkedHashMap<>();
        results.forEach((name, result) -> {
            matchResults.put(name, result.getReversValue());
        });
        this.gamerMatchResults = matchResults;
    }

    public static GamerMatchResultsDto from(BlackJackGame blackJackGame) {
        return new GamerMatchResultsDto(blackJackGame.getGamerMatchResults());
    }

    public Map<String, String> getGamerMatchResults() {
        return gamerMatchResults;
    }
}
