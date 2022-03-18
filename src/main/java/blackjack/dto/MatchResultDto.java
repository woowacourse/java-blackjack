package blackjack.dto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class MatchResultDto {

    private final Map<String, Integer> playerOutcomes;
    private final int dealerOutcome;

    private MatchResultDto(final Map<String, Integer> playerOutcomes,
                           final int dealerOutcome) {
        this.playerOutcomes = new LinkedHashMap<>(playerOutcomes);
        this.dealerOutcome = dealerOutcome;
    }

    public static MatchResultDto toDto(final MatchResult matchResult) {
        return new MatchResultDto(matchResult.getPlayerOutcomes(), matchResult.getDealerOutcome());
    }

    public Map<String, Integer> getPlayerOutcomes() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(playerOutcomes));
    }

    public int getDealerOutcome() {
        return dealerOutcome;
    }

}
