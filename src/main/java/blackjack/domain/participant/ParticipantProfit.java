package blackjack.domain.participant;

public class ParticipantProfit {

    private final String participantName;
    private final int profit;

    public ParticipantProfit(String participantName, int profit) {
        this.participantName = participantName;
        this.profit = profit;
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getProfit() {
        return profit;
    }
}
