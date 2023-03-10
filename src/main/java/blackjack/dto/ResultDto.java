package blackjack.dto;

import blackjack.model.participant.Participant;

public class ResultDto {

    private final String name;
    private final String profit;

    public ResultDto(String name, String profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ResultDto of(Participant participant, int profit) {
        return new ResultDto(participant.getName(), Integer.toString(profit));
    }

    public String getName() {
        return name;
    }

    public String getProfit() {
        return profit;
    }
}
