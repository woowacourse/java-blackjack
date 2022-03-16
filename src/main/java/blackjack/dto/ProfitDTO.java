package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Profit;

public class ProfitDTO {

    private final String name;
    private final double profit;

    public ProfitDTO(Participant participant, Profit profit) {
        this.name = participant.getName();
        this.profit = profit.getValue();
    }

    public ProfitDTO(Participant participant, double profit) {
        this.name = participant.getName();
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
