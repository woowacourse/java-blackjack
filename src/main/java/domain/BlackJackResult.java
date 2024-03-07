package domain;

import java.util.Map;
import java.util.Set;

public class BlackJackResult {
    private Map<Participant, Boolean> resultByParticipant;

    public BlackJackResult(Map<Participant, Boolean> resultByParticipant) {
        this.resultByParticipant = resultByParticipant;
    }

    public Set<Map.Entry<Participant, Boolean>> getEntry() {
        return resultByParticipant.entrySet();
    }

    public int getDealerWinCount() {
        long dealerWinCount = resultByParticipant.values().stream()
                .filter(isWin -> !isWin)
                .count();
        return (int) dealerWinCount;
    }

    public int getTotalCount() {
        return resultByParticipant.size();
    }
}
