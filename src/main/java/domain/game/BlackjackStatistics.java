package domain.game;

import java.util.List;

public record BlackjackStatistics(
        int dealerProfit,
        List<PlayerProfit> playerProfits
) {
}
