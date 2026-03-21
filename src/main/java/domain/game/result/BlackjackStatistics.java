package domain.game.result;

import java.util.List;

public record BlackjackStatistics(
        int dealerProfit,
        List<PlayerProfit> playerProfits
) {
}
