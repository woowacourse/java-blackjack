package blackjack.domain.dto;

import blackjack.domain.Participant;

public class BettingResultDto {
    private final String name;
    private final int revenue;

    private BettingResultDto(String name, int revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public static BettingResultDto of(Participant participant, int revenue) {
        return new BettingResultDto(participant.getName(), revenue);
    }

    public String getName() {
        return name;
    }

    public int getRevenue() {
        return revenue;
    }
}
