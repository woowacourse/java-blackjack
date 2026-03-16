package dto;

import domain.participant.Money;
import java.util.List;

public record MoneyDto(String dealerMoney, List<PlayerOutcomeDto> playerOutcomes) {
    public static MoneyDto from(Money dealerProfit, List<PlayerOutcomeDto> playerOutcomes) {
        return new MoneyDto(String.valueOf(dealerProfit.getAmount()), playerOutcomes);
    }
}
