package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

final class DealerNormalCaseMatcher extends Matcher {

    public DealerNormalCaseMatcher(Dealer dealer) {
        super(dealer);
    }

    @Override
    protected Result playerResult(Dealer dealer, Gamer gamer) {
        if (gamer.isBust()) {
            return Result.loss(gamer.bettingMoney());
        } else if (gamer.isBlackjack()) {
            return Result.blackjack(gamer.bettingMoney());
        }
        return compare(dealer, gamer);
    }

    private Result compare(Dealer dealer, Gamer gamer) {
        if (gamer.lessScoreThan(dealer)) {
            return Result.loss(gamer.bettingMoney());
        } else if (gamer.moreScoreThan(dealer)) {
            return Result.win(gamer.bettingMoney());
        }
        return Result.draw(gamer.bettingMoney());
    }
}
