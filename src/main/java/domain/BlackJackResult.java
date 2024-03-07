package domain;

import java.util.Map;
import java.util.Set;

public class BlackJackResult {

    private final Map<Participant, WinStatus> resultByParticipant;

    public BlackJackResult(final Map<Participant, WinStatus> resultByParticipant) {
        this.resultByParticipant = resultByParticipant;
    }

    public Set<Map.Entry<Participant, WinStatus>> getEntry() {
        return resultByParticipant.entrySet();
    }

    public int getDealerWinCount() {
        long dealerWinCount = resultByParticipant.values().stream()
                .filter(isWin -> WinStatus.LOSE == isWin)
                .count();
        return (int) dealerWinCount;
    }

    public int getTotalCount() {
        return resultByParticipant.size();
    }
}
