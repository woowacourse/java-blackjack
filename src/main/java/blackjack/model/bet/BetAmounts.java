package blackjack.model.bet;

import blackjack.model.gameresult.GameResult;
import blackjack.model.user.Player;
import java.util.Map;

public class BetAmounts {

    private final Map<Player, BetAmount> betAmounts;

    public BetAmounts(Map<Player, BetAmount> betAmounts) {
        this.betAmounts = Map.copyOf(betAmounts);
    }

    public int calculateProfit(Player player, GameResult gameResult) {
        BetAmount betAmount = betAmounts.get(player);
        if (gameResult == GameResult.BLACKJACK_WIN) {
            return (int) Math.round(betAmount.getAmount() * 1.5);
        }

        if (gameResult == GameResult.WIN) {
            return betAmount.getAmount();
        }

        if (gameResult == GameResult.LOSE) {
            return -betAmount.getAmount();
        }

        return 0;
    }
}
