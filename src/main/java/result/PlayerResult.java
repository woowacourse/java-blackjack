package result;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import player.Dealer;
import player.Player;

public class PlayerResult {

    private final Map<Player, MatchResult> matchResults;

    public PlayerResult(Dealer dealer, List<Player> participants) {
        this.matchResults = computeParticipantsMatchResult(dealer, participants);
    }

    public Map<Player, MatchResult> computeParticipantsMatchResult(Dealer dealer, List<Player> participants) {
        Map<Player, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();

        for (Player participant : participants) {
            MatchResult matchResult = MatchResult.calculateParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant, matchResult);
        }
        return participantNameAndMatchResult;
    }

    public Map<MatchResult, Integer> computeDealerMatchResult() {
        Map<MatchResult, Integer> matchResultCount = new EnumMap<>(MatchResult.class);

        matchResults.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));

        return matchResultCount;
    }

    public Map<Player, MatchResult> getMatchResults() {
        return matchResults;
    }
}
