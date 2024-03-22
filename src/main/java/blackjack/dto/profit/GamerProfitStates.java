package blackjack.dto.profit;

import java.util.List;

public record GamerProfitStates(List<GamerProfitState> gamerProfitStates, int dealerProfit) {
}
