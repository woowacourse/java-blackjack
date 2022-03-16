package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.ResultStatus.LOSS;
import static blackjack.model.player.matcher.ResultStatus.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerBustCaseMatcher extends Matcher {

    public DealerBustCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected ResultStatus playerResultStatus(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return LOSS;
        }
        return WIN;
    }
}
