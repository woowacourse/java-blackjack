package domain.result;

import domain.betiing.BettingProfit;
import domain.participant.ParticipantName;

public class BettingResult {

    private final ParticipantName participantName;
    private final BettingProfit bettingProfit;

    private BettingResult(ParticipantName participantName, BettingProfit bettingProfit) {
        this.participantName = participantName;
        this.bettingProfit = bettingProfit;
    }

    public static BettingResult of(ParticipantName participantName, BettingProfit bettingProfit) {
        return new BettingResult(participantName, bettingProfit);
    }

    public ParticipantName getParticipantName() {
        return participantName;
    }

    public double getProfit() {
        return bettingProfit.getProfit();
    }
}
