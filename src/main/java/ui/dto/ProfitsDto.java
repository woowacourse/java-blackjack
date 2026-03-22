package ui.dto;

import domain.dto.Profit;
import domain.participant.BetMoney;
import java.math.BigDecimal;
import java.util.List;

public record ProfitsDto(
        BigDecimal dealerProfit,
        List<PlayerDtoWithProfit> players
) {
    public static ProfitsDto toDto(List<Profit> profits, BetMoney dealerProfit) {
        return new ProfitsDto(dealerProfit.getValue(), PlayerDtoWithProfit.toDtoList(profits));
    }


}
