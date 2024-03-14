package blackjack.domain.handrank;

import blackjack.domain.money.BetAmount;
import blackjack.domain.money.Profit;
import java.util.function.Function;

public enum SingleMatchResult {

    PLAYER_BLACKJACK(amount -> Profit.win(amount, 1.5)),
    PLAYER_WIN(Profit::win),
    DRAW(amount -> Profit.ZERO),
    DEALER_WIN(Profit::lose);

    private final Function<BetAmount, Profit> playerProfitFunction;

    SingleMatchResult(Function<BetAmount, Profit> playerProfitFunction) {
        this.playerProfitFunction = playerProfitFunction;
    }

    public Profit calculatePlayerProfit(BetAmount betAmount) {
        return playerProfitFunction.apply(betAmount);
    }

    public Profit calculateDealerProfit(BetAmount betAmount) {
        return calculatePlayerProfit(betAmount).reverse();
    }
}
