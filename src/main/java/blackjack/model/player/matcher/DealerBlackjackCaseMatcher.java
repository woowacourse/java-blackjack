package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.ResultStatus.DRAW;
import static blackjack.model.player.matcher.ResultStatus.LOSS;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerBlackjackCaseMatcher extends Matcher {

    public DealerBlackjackCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected ResultStatus playerResultStatus(Dealer dealer, Gamer gamer) {
        if (gamer.isBlackjack()) {
            return DRAW;
        }
        return LOSS;
    }
}
