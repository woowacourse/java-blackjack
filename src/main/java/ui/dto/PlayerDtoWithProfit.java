package ui.dto;

import domain.dto.Profit;
import java.math.BigDecimal;
import java.util.List;

public record PlayerDtoWithProfit(
        String name,
        List<CardDto> cards,
        int score,
        BigDecimal profit
) {

    public static PlayerDtoWithProfit toDto(Profit profit) {
        return new PlayerDtoWithProfit(profit.player().getName().getValue(),
                CardDto.toDtoList(profit.player().getCards()),
                profit.player().totalSum().value(),
                profit.betMoney().getValue());
    }

    public static List<PlayerDtoWithProfit> toDtoList(List<Profit> profits) {
        return profits.stream()
                .map(PlayerDtoWithProfit::toDto)
                .toList();
    }
}
