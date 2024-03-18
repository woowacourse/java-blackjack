package blackjack.domain.handrank;

import blackjack.domain.money.BetAmount;
import blackjack.domain.money.Profit;

public enum SingleMatchResult {

    PLAYER_BLACKJACK(1.5),
    PLAYER_WIN(1),
    DRAW(0),
    DEALER_WIN(-1);

    private final double playerMultiplier;

    SingleMatchResult(double playerMultiplier) {
        this.playerMultiplier = playerMultiplier;
    }

    public Profit calculatePlayerProfit(BetAmount betAmount) {
        return Profit.of(betAmount, playerMultiplier);
    }
}
