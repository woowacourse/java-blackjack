package blackjack.model.player.matcher;

import static blackjack.model.player.matcher.Result.BLACKJACK;
import static blackjack.model.player.matcher.Result.DRAW;
import static blackjack.model.player.matcher.Result.LOSS;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class BlackjackCaseMatcher extends Matcher {

    public BlackjackCaseMatcher(Dealer dealer, Gamer gamer) {
        super(dealer, gamer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (isBothBlackjack(dealer, gamer)) {
            return DRAW;
        } else if(gamer.isBlackjack()) {
            return BLACKJACK;
        }
        return LOSS;
    }

    private boolean isBothBlackjack(Dealer dealer, Gamer gamer) {
        return dealer.isBlackjack() && gamer.isBlackjack();
    }

}
