package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerBlackjackCaseMatcher extends Matcher {

    public DealerBlackjackCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (gamer.isBlackjack()) {
            return Result.draw(gamer.bettingMoney());
        }
        return Result.loss(gamer.bettingMoney());
    }
}
