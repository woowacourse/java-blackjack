package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerBustCaseMatcher extends Matcher {

    public DealerBustCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return Result.loss(gamer.bettingMoney());
        }
        if (gamer.isBlackjack()) {
            return Result.blackjack(gamer.bettingMoney());
        }
        return Result.win(gamer.bettingMoney());
    }
}
