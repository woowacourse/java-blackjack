package blackjack.domain;

import blackjack.domain.participant.Participant;

public class ParticipantProfit {

    private final String participantName;
    private final int profit;

    public ParticipantProfit(String participantName, int profit) {
        this.participantName = participantName;
        this.profit = profit;
    }

    public static ParticipantProfit of(Participant participant, int profit) {
        return new ParticipantProfit(participant.getName(), profit);
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getProfit() {
        return profit;
    }
}
