package blackjack.model.result;

import static blackjack.model.result.ResultCommand.*;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public class Referee {
    private final Dealer dealer;

    public Referee(final Dealer dealer) {
        this.dealer = dealer;
    }

    public ResultCommand judgePlayerResult(final Player player) {
        return calculateResult(player);
    }

    private ResultCommand calculateResult(final Player player) {
        if (dealer.isBlackJack()) {
            return judgePlayerResultWhenDealerBlackJack(player);
        }
        if (player.isBlackJack()) {
            return BLACK_JACK_WIN;
        }
        if (dealer.isBust()) {
            return judgePlayerResultWhenDealerBust(player);
        }
        return judgePlayerResultWhenDealerNotBust(player);
    }

    private ResultCommand judgePlayerResultWhenDealerBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return DRAW;
        }
        return LOSE;
    }

    private ResultCommand judgePlayerResultWhenDealerBust(final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private ResultCommand judgePlayerResultWhenDealerNotBust(final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.notifyScore() > dealer.notifyScore()) {
            return WIN;
        }
        if (player.notifyScore() < dealer.notifyScore()) {
            return LOSE;
        }
        return judgePlayerResultWhenSameScore(player);
    }

    private ResultCommand judgePlayerResultWhenSameScore(final Player player) {
        if (player.hasManyCardsThan(dealer)) {
            return LOSE;
        }
        if (player.hasSameCardsSizeThan(dealer)) {
            return DRAW;
        }
        return WIN;
    }
}
