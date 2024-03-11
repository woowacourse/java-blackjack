package blackjack.domain.result;

public class ParticipantProfit {
    private final String participantName;
    private final double profit;

    public ParticipantProfit(String participantName, double profit) {
        this.participantName = participantName;
        this.profit = profit;
    }

    public String getParticipantName() {
        return participantName;
    }

    public double getProfit() {
        return profit;
    }

    public double getNegatedProfit() {
        return profit * -1;
    }
}
