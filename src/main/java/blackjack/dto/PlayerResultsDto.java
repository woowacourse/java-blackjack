package blackjack.dto;

import blackjack.model.money.Money;
import java.util.List;

public record PlayerResultsDto(List<PlayerResultDto> results) {

    public Money getDealerProfit() {
        Money dealerProfit = Money.zero();
        for (PlayerResultDto result : results) {
            dealerProfit = dealerProfit.minus(result.profit());
        }
        return dealerProfit;
    }
}
