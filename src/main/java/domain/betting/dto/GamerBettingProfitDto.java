package domain.betting.dto;

import domain.betting.Money;
import domain.gamer.Gamer;

public record GamerBettingProfitDto(
        String gamerName,
        double bettingProfit
) {

    public static GamerBettingProfitDto of(Gamer gamer, Money bettingProfit) {
        return new GamerBettingProfitDto(gamer.getMyName(), bettingProfit.money());
    }

}
