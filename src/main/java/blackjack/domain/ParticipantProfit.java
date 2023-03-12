package blackjack.domain;

import blackjack.domain.participant.Participant;

public class ParticipantProfit {
    private final Participant participant;
    private final int profit;

    public ParticipantProfit(Participant participant, int profit) {
        this.participant = participant;
        this.profit = profit;
    }

    public static ParticipantProfit of(Participant participant, Bet bet) {
        return new ParticipantProfit(participant, bet.getBet());
    }

    public String getParticipantName() {
        return participant.getName().getName();
    }

    public int getProfit() {
        return profit;
    }
}
