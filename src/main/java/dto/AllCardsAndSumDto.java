package dto;

import java.util.Map;

public class AllCardsAndSumDto {
    private final Map<ParticipatorDto, Integer> playerResults;
    private final ParticipatorDto dealer;
    private final int dealerSum;

    public AllCardsAndSumDto(Map<ParticipatorDto, Integer> playerCardsAndSum, ParticipatorDto dealer, int dealerSum) {
        this.playerResults = playerCardsAndSum;
        this.dealer = dealer;
        this.dealerSum = dealerSum;
    }

    public Map<ParticipatorDto, Integer> getPlayerResults() {
        return playerResults;
    }

    public ParticipatorDto getDealer() {
        return dealer;
    }

    public int getDealerSum() {
        return dealerSum;
    }
}
