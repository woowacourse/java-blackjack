package blackjack.model.betting;

import static blackjack.model.betting.ProfitRate.MINUS_100_PERCENT;
import static blackjack.model.betting.ProfitRate.PLUS_100_PERCENT;
import static blackjack.model.betting.ProfitRate.PLUS_150_PERCENT;
import static blackjack.model.betting.ProfitRate.ZERO;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.WIN;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;

public class BettingRule {

    public ProfitRate calculateProfitRate(final Player player, final ResultCommand resultCommand) {
        if (resultCommand.equals(WIN)) {
            return calculateProfitRateWhenPlayerWin(player);
        }

        return calculateProfitRateWhenPlayerNotWin(resultCommand);
    }

    private ProfitRate calculateProfitRateWhenPlayerWin(final Player player) {
        if (player.isBlackJack()) {
            return PLUS_150_PERCENT;
        }

        return PLUS_100_PERCENT;
    }

    private ProfitRate calculateProfitRateWhenPlayerNotWin(final ResultCommand resultCommand) {
        if (resultCommand.equals(DRAW)) {
            return ZERO;
        }
        return MINUS_100_PERCENT;
    }
}
