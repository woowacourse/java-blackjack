package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;

public class BlackjackEarningCalculator {

    private static final double BLACKJACK_REVENUE_RATIO = 1.5D;
    private static final double LOSE_REVENUE_RATIO = -1.0D;

    private final PlayerResultHandler playerResultHandler;

    private BlackjackEarningCalculator(PlayerResultHandler playerResultHandler) {
        this.playerResultHandler = playerResultHandler;
    }

    public static BlackjackEarningCalculator fromDealer(Dealer dealer) {
        PlayerResultHandler resultHandler = new PlayerResultHandler(dealer);

        return new BlackjackEarningCalculator(resultHandler);
    }

    public Money calculateDealerEarning(Players players) {
        List<Player> losePlayers = playerResultHandler.getLosePlayers(players.getPlayers());

        if (losePlayers.isEmpty()) {
            return Money.getZeroAmountMoney();
        }

        return losePlayers.stream()
                .map(players::getBetAmount)
                .reduce(Money.getZeroAmountMoney(), Money::add);
    }

    public Money calculatePlayerEarning(Player player, Money betAmount) {
        GameResult gameResult = playerResultHandler.getPlayerResult(player);

        return getPlayerEarning(gameResult, betAmount);
    }

    private Money getPlayerEarning(GameResult gameResult, Money betAmount) {
        if (gameResult == GameResult.BLACKJACK_WIN) {
            return betAmount.multiplyByRatio(BLACKJACK_REVENUE_RATIO);
        }
        if (gameResult == GameResult.WIN) {
            return betAmount;
        }
        if (gameResult == GameResult.LOSE) {
            return betAmount.multiplyByRatio(LOSE_REVENUE_RATIO);
        }
        return Money.getZeroAmountMoney();
    }
}
