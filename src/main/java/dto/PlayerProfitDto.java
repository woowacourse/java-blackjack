package dto;

import domain.bet.Profit;
import domain.participant.Name;

public record PlayerProfitDto(String name, long profit) {

    public static PlayerProfitDto from(Name name, Profit profit) {
        return new PlayerProfitDto(name.name(), profit.amount());
    }
}
