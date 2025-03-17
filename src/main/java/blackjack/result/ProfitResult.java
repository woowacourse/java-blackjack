package blackjack.result;

import blackjack.gamer.Player;
import java.math.BigDecimal;
import java.util.Map;

public class ProfitResult {
    private final BigDecimal dealerProfit;
    private final Map<Player, BigDecimal> playerProfits;

    public ProfitResult(BigDecimal dealerProfit, Map<Player, BigDecimal> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public BigDecimal getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, BigDecimal> getPlayerProfits() {
        return playerProfits;
    }
}
