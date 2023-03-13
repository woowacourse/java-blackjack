package blackjack.domain.result;

import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.math.BigDecimal;

public enum JudgeResult {

    BLACKJACK_WIN(new BigDecimal("1.5")),
    WIN(new BigDecimal("1.0")),
    PUSH(new BigDecimal("0")),
    LOSE(new BigDecimal("-1.0"));

    private final BigDecimal profitRate;

    JudgeResult(final BigDecimal profitRate) {
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

    public BigDecimal profit(final BettingMoney bettingMoney) {
        return profitRate.multiply(bettingMoney.getAmount());
    }
}
