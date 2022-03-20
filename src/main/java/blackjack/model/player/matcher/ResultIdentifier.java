package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

public enum ResultIdentifier {

    DEALER_BLACKJACK_CASE{
        @Override
        public Result identify(Dealer dealer, Gamer gamer) {
            if (gamer.isBlackjack()) {
                return Result.draw(gamer.bettingMoney());
            }
            return Result.loss(gamer.bettingMoney());
        }
    },
    DEALER_BUST_CASE{
        @Override
        public Result identify(Dealer dealer, Gamer gamer) {
            if (gamer.isBust()) {
                return Result.loss(gamer.bettingMoney());
            }
            if (gamer.isBlackjack()) {
                return Result.blackjack(gamer.bettingMoney());
            }
            return Result.win(gamer.bettingMoney());
        }
    },
    DEALER_NORMAL_CASE{
        @Override
        public Result identify(Dealer dealer, Gamer gamer) {
            if (gamer.isBust()) {
                return Result.loss(gamer.bettingMoney());
            }
            if (gamer.isBlackjack()) {
                return Result.blackjack(gamer.bettingMoney());
            }
            return compareWithScore(dealer, gamer);
        }

        private Result compareWithScore(Dealer dealer, Gamer gamer) {
            if (gamer.lessScoreThan(dealer)) {
                return Result.loss(gamer.bettingMoney());
            }
            if (gamer.moreScoreThan(dealer)) {
                return Result.win(gamer.bettingMoney());
            }
            return Result.draw(gamer.bettingMoney());
        }
    };

    public abstract Result identify(Dealer dealer, Gamer gamer);

    public static ResultIdentifier of(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return DEALER_BLACKJACK_CASE;
        }
        if (dealer.isBust()) {
            return DEALER_BUST_CASE;
        }
        return DEALER_NORMAL_CASE;
    }
}
