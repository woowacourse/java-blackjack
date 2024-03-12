package blackjack.dto;

import java.util.List;

public record BlackjackResult(String dealerProfit, List<PlayerProfit> playerProfits) {
}
