package match;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import player.Dealer;
import player.Participant;

public class MatchResults {
    private final Map<Participant, MatchResult> matchResults;

    public MatchResults(Dealer dealer, List<Participant> participants) {
        this.matchResults = computeParticipantsMatchResult(dealer, participants);
    }

    private Map<Participant, MatchResult> computeParticipantsMatchResult(Dealer dealer, List<Participant> participants) {
        Map<Participant, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();

        for (Participant participant : participants) {
            MatchResult matchResult = MatchResult.calculateParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant, matchResult);
        }
        return participantNameAndMatchResult;
    }

    public Map<Participant, MatchResult> getMatchResults() {
        return matchResults;
    }
}
