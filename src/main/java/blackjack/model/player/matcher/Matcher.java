package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Money;

public abstract class Matcher {
    private final Dealer dealer;
    private final Gamer gamer;

    public Matcher(Dealer dealer, Gamer gamer) {
        this.dealer = dealer;
        this.gamer = gamer;
    }

    public final Record match() {
        Result result = playerResult(dealer, gamer);
        Money profit = result.profit(gamer.bettingMoney());
        return new Record(gamer.name(), profit);
    }

    protected abstract Result playerResult(Dealer dealer, Gamer gamer);

    public static Matcher of(Dealer dealer, Gamer gamer) {
        if (hasBlackjack(dealer, gamer)) {
            return new BlackjackCaseMatcher(dealer, gamer);
        } else if(hasBust(dealer, gamer)) {
            return new BustCaseMatcher(dealer, gamer);
        }
        return new NormalCaseMatcher(dealer, gamer);
    }

    private static boolean hasBlackjack(Dealer dealer, Gamer gamer) {
        return dealer.isBlackjack() || gamer.isBlackjack();
    }

    private static boolean hasBust(Dealer dealer, Gamer gamer) {
        return dealer.isBust() || gamer.isBust();
    }
}
