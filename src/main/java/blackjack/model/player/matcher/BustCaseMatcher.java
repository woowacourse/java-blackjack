package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.Result.LOSS;
import static blackjack.model.player.matcher.Result.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class BustCaseMatcher extends Matcher {

    public BustCaseMatcher(Dealer dealer, Gamer gamer) {
        super(dealer, gamer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return LOSS;
        }
        return WIN;
    }

}
