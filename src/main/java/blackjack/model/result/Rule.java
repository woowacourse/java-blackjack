package blackjack.model.result;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public class Rule {
    private final Dealer dealer;

    public Rule(final Dealer dealer) {
        this.dealer = dealer;
    }

    public ResultCommand calculateResult(final Player player) {
        if (dealer.isBust()) {
            return judgePlayerResultWhenDealerBust(player);
        }
        return judgePlayerResultWhenDealerNotBust(player);
    }

    private ResultCommand judgePlayerResultWhenDealerBust(final Player player) {
        if (player.isBust()) {
            return ResultCommand.LOSE;
        }
        return ResultCommand.WIN;
    }

    private ResultCommand judgePlayerResultWhenDealerNotBust(final Player player) {
        if (player.isBust()) {
            return ResultCommand.LOSE;
        }
        if (player.notifyScore() > dealer.notifyScore()) {
            return ResultCommand.WIN;
        }
        if (player.notifyScore() < dealer.notifyScore()) {
            return ResultCommand.LOSE;
        }
        return judgePlayerResultWhenSameScore(player);
    }

    private ResultCommand judgePlayerResultWhenSameScore(final Player player) {
        if (player.hasManyCardsThan(dealer)) {
            return ResultCommand.LOSE;
        }
        if (player.hasSameCardsSizeThan(dealer)) {
            return ResultCommand.DRAW;
        }
        return ResultCommand.WIN;
    }
}
