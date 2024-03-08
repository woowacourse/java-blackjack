package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BlackJackResult {

    private final LinkedHashMap<Participant, WinStatus> resultByParticipant;

    public BlackJackResult(final LinkedHashMap<Participant, WinStatus> resultByParticipant) {
        this.resultByParticipant = resultByParticipant;
    }

    public int getDealerWinCount() {
        long dealerWinCount = resultByParticipant.values().stream()
                .filter(isWin -> WinStatus.LOSE == isWin)
                .count();
        return (int) dealerWinCount;
    }

    public Set<Map.Entry<Participant, WinStatus>> getEntry() {
        return resultByParticipant.entrySet();
    }

    public int getTotalCount() {
        return resultByParticipant.size();
    }
}
