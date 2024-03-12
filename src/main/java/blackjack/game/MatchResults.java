package blackjack.game;

import blackjack.participant.Participant;
import java.util.HashMap;
import java.util.Map;

public class MatchResults {

    private final Map<String, MatchResult> results;

    public MatchResults() {
        this.results = new HashMap<>();
    }

    public void addResult(String playerName, Participant player, Participant dealer) {
        MatchResult result = player.matchResultVersus(dealer);
        results.put(playerName, result);
    }

    public MatchResult getResultByName(String playerName) {
        if (!results.containsKey(playerName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 이름입니다.");
        }
        return results.get(playerName);
    }

    public int getResultCount(MatchResult result) {
        return (int) results.values()
                .stream()
                .filter(matchResult -> matchResult == result)
                .count();
    }
}
