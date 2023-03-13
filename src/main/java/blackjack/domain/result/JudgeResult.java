package blackjack.domain.result;

import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public enum JudgeResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double profitRate;

    JudgeResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    public static JudgeResult of(final Player targetPlayer, final Dealer dealer) {
        if (targetPlayer.isBust()) {
            return JudgeResult.LOSE;
        }
        if (dealer.isBust()) {
            return JudgeResult.WIN;
        }
        if (!dealer.isBlackJack() && targetPlayer.isBlackJack()) {
            return JudgeResult.BLACKJACK_WIN;
        }
        return matchWithoutBlackJackConsider(targetPlayer.computeCardsScore(), dealer.computeCardsScore());
    }

    private static JudgeResult matchWithoutBlackJackConsider(final int selfScore, final int counterScore) {
        if (selfScore > counterScore) {
            return WIN;
        }
        if (selfScore < counterScore) {
            return LOSE;
        }
        return PUSH;
    }

    public int profit(final BettingMoney bettingMoney) {
        return (int) (profitRate * (bettingMoney.getAmount()));
    }
}
