package blackjack.dto;

import java.util.Map;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class MatchResultDto {

    private final Map<MatchStatus, Integer> resultOfDealer;
    private final Map<String, MatchStatus> resultOfPlayers;

    private MatchResultDto(final Map<MatchStatus, Integer> resultOfDealer,
                           final Map<String, MatchStatus> resultOfPlayers) {
        this.resultOfDealer = resultOfDealer;
        this.resultOfPlayers = resultOfPlayers;
    }

    public static MatchResultDto toDto(final MatchResult matchResult) {
        return new MatchResultDto(matchResult.getResultOfDealer(), matchResult.getResultOfPlayers());
    }

    public Map<MatchStatus, Integer> getResultOfDealer() {
        return resultOfDealer;
    }

    public Map<String, MatchStatus> getResultOfPlayers() {
        return resultOfPlayers;
    }

}
