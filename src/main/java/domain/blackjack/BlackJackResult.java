package domain.blackjack;

import domain.participant.Participant;

import java.util.LinkedHashMap;

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

    public int getTotalCount() {
        return resultByParticipant.size();
    }

    public WinStatus getWinStatus(Participant participant) {
        return resultByParticipant.get(participant);
    }
}
