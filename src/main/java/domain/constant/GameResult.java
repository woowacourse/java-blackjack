package domain.constant;

import domain.game.BettingMoney;

import java.util.function.Function;

public enum GameResult {
    BLACKJACK_WIN(bettingMoney -> (int) (bettingMoney.value() * 1.5)),
    WIN(BettingMoney::value),
    LOSE(bettingMoney -> -bettingMoney.value()),
    DRAW((bettingMoney) -> 0);

    private final Function<BettingMoney, Integer> revenueRole;

    GameResult(final Function<BettingMoney, Integer> revenueRole) {
        this.revenueRole = revenueRole;
    }

    public static GameResult getWinResult(final boolean isBlackJack) {
        if (isBlackJack) {
            return BLACKJACK_WIN;
        }

        return WIN;
    }

    public int calculateRevenue(final BettingMoney bettingMoney) {
        return revenueRole.apply(bettingMoney);
    }
}
