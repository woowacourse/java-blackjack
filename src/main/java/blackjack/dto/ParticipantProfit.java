package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Profit;

public class ParticipantProfit {

    private final String participantName;
    private final int profit;

    public ParticipantProfit(String participantName, int profit) {
        this.participantName = participantName;
        this.profit = profit;
    }

    public static ParticipantProfit of(Participant participant, Profit profit) {
        return new ParticipantProfit(participant.getName(), profit.getAmount());
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getProfit() {
        return profit;
    }
}
