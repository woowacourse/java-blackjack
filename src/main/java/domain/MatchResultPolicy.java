package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchResultPolicy {

    public static Map<MatchResult, Integer> computeDealerMatchResultCount(
            Map<Player, MatchResult> participantNameAndMatchResult) {
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));

        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));
        return matchResultCount;
    }

    public static Map<Player, MatchResult> computeParticipantsMatchResult(Dealer dealer,
                                                                          List<Player> participants) {
        Map<Player, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        for (Player participant : participants) {
            MatchResult matchResult = computeParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant, matchResult);
        }
        return participantNameAndMatchResult;
    }

    private static MatchResult computeParticipantMatchResult(Dealer dealer, Player participant) {
        if (participant.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }
}

