package blackjack.dto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class MatchResultDto {

    private final Map<MatchStatus, Integer> resultOfDealer;
    private final Map<String, MatchStatus> resultOfPlayers;

    private MatchResultDto(final Map<MatchStatus, Integer> resultOfDealer,
                           final Map<String, MatchStatus> resultOfPlayers) {
        this.resultOfDealer = new EnumMap<>(resultOfDealer);
        this.resultOfPlayers = new LinkedHashMap<>(resultOfPlayers);
    }

    public static MatchResultDto toDto(final MatchResult matchResult) {
        return new MatchResultDto(matchResult.getResultOfDealer(), matchResult.getResultOfPlayers());
    }

    public Map<MatchStatus, Integer> getResultOfDealer() {
        return Collections.unmodifiableMap(new EnumMap<>(resultOfDealer));
    }

    public Map<String, MatchStatus> getResultOfPlayers() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(resultOfPlayers));
    }

}
