package blackjack.domain.result;

import blackjack.domain.participant.Name;

public class ParticipantProfit {
    private final Name participantName;
    private final double profit;

    public ParticipantProfit(Name participantName, double profit) {
        this.participantName = participantName;
        this.profit = profit;
    }

    public String getParticipantName() {
        return participantName.getValue();
    }

    public double getProfit() {
        return profit;
    }

    public double getNegatedProfit() {
        return profit * -1;
    }
}
