package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.Result.DRAW;
import static blackjack.model.player.matcher.Result.LOSS;
import static blackjack.model.player.matcher.Result.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class NormalCaseMatcher extends Matcher {

    public NormalCaseMatcher(Dealer dealer, Gamer gamer) {
        super(dealer, gamer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (gamer.lessScoreThan(dealer)) {
            return LOSS;
        } else if (gamer.moreScoreThan(dealer)) {
            return WIN;
        }
        return DRAW;
    }

}
