package blackjack.dto;

import java.util.List;

public record GamerProfitStates(List<PlayerProfitState> playerProfitStates, int dealerProfit) {
}
