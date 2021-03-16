package blackjack.domain;

import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;


public class BlackJackResult {
    private final Map<Player, MatchResult> result;

    public BlackJackResult(Map<Player, MatchResult> result) {
        this.result = result;
    }

    public Map<MatchResult, Integer> getDealerResult() {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);

        for (MatchResult matchResult : MatchResult.values()) {
            dealerResult.put(matchResult,
                    (int) result.values()
                            .stream()
                            .filter(result -> result.equals(MatchResult.reverseMatchResult(matchResult)))
                            .count());
        }

        return dealerResult;
    }

    public Map<Player, MatchResult> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
