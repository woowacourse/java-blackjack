package domain.betting.dto;

import domain.betting.Profit;
import domain.gamer.Gamer;

public record GamerBettingProfitDto(
        String gamerName,
        int bettingProfit
) {

    public static GamerBettingProfitDto of(Gamer gamer, Profit profit) {
        return new GamerBettingProfitDto(gamer.getName(), profit.getProfit());
    }

}
