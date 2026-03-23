package ui.dto;

import domain.dto.Profit;
import domain.participant.BetMoney;
import domain.participant.Dealer;
import java.math.BigDecimal;
import java.util.List;

public record GameResultDto(
        List<CardDto> dealerCards,
        BigDecimal dealerProfit,
        int dealerScore,
        List<PlayerDtoWithProfit> players
) {
    public static GameResultDto toDto(Dealer dealer, List<Profit> profits, BetMoney dealerProfit) {
        return new GameResultDto(CardDto.toDtoList(dealer.getState().hand().getCards()),
                dealerProfit.getValue(),
                dealer.totalSum().value(),
                PlayerDtoWithProfit.toDtoList(profits));
    }
}
