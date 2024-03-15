package blackjack.model.betting;

import static blackjack.model.betting.ProfitRate.LOSE_RATE;
import static blackjack.model.betting.ProfitRate.NOT_BLACKJACK_BUT_WIN_RATE;
import static blackjack.model.betting.ProfitRate.BLACKJACK_RATE;
import static blackjack.model.betting.ProfitRate.DRAW_RATE;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.WIN;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;

public class BettingRule {

    public ProfitRate calculateProfitRate(final Player player, final ResultCommand resultCommand) {
        if (resultCommand == WIN) {
            return calculateProfitRateWhenPlayerWin(player);
        }

        return calculateProfitRateWhenPlayerNotWin(resultCommand);
    }

    private ProfitRate calculateProfitRateWhenPlayerWin(final Player player) {
        if (player.isBlackJack()) {
            return BLACKJACK_RATE;
        }

        return NOT_BLACKJACK_BUT_WIN_RATE;
    }

    private ProfitRate calculateProfitRateWhenPlayerNotWin(final ResultCommand resultCommand) {
        if (resultCommand == DRAW) {
            return DRAW_RATE;
        }
        return LOSE_RATE;
    }
}
