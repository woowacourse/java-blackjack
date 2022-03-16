package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

public abstract class Matcher {

    private final Dealer dealer;

    public Matcher(Dealer dealer) {
        this.dealer = dealer;
    }

    public final Record match(Gamer gamer) {
        ResultStatus status = playerResultStatus(dealer, gamer);
        Result result = new Result(status, gamer.bettingMoney());
        return new Record(gamer.name(), result);
    }

    protected abstract ResultStatus playerResultStatus(Dealer dealer, Gamer gamer);

    public static Matcher of(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return new DealerBlackjackCaseMatcher(dealer);
        } else if (dealer.isBust()) {
            return new DealerBustCaseMatcher(dealer);
        }
        return new DealerNormalCaseMatcher(dealer);
    }
}
