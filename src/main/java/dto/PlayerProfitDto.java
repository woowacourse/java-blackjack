package dto;

import domain.betting.Profit;

public record PlayerProfitDto(String name, long profit) {
    public static PlayerProfitDto from(Profit profit) {
        return new PlayerProfitDto(profit.getName(),
                (long) (profit.getEarningRate() * profit.getMoney().getValue()));
    }
}
