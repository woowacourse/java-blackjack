package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.ResultStatus.BLACKJACK;
import static blackjack.model.player.matcher.ResultStatus.DRAW;
import static blackjack.model.player.matcher.ResultStatus.LOSS;
import static blackjack.model.player.matcher.ResultStatus.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerNormalCaseMatcher extends Matcher {

    public DealerNormalCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected ResultStatus playerResultStatus(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return LOSS;
        } else if (gamer.isBlackjack()) {
            return BLACKJACK;
        }
        return compare(dealer, gamer);
    }

    private ResultStatus compare(Dealer dealer, Gamer gamer) {
        if (gamer.lessScoreThan(dealer)) {
            return LOSS;
        } else if (gamer.moreScoreThan(dealer)) {
            return WIN;
        }
        return DRAW;
    }
}
