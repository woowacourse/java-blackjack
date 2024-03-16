package view.dto;

import model.player.Participant;

public class UserProfitDto {

    private final String name;
    private final int profit;

    public UserProfitDto(Participant participant, Double profit) {
        this.name = participant.getName().getValue();
        this.profit = profit.intValue();
    }

    public UserProfitDto(String name, Double profit) {
        this.name = name;
        this.profit = profit.intValue();
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
