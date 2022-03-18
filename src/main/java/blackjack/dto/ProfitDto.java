package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Profit;

public class ProfitDto {

    private final String name;
    private final double profit;

    public ProfitDto(Participant participant, Profit profit) {
        this.name = participant.getName();
        this.profit = profit.getValue();
    }

    public ProfitDto(Participant participant, double profit) {
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
