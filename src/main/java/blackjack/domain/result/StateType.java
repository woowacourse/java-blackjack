package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;

public enum StateType {
    BLACKJACK {
        @Override
        int calculateProfit(Gamer gamer, Dealer dealer) {
            int profit = gamer.profit();
            if (dealer.isBlackjack()) {
                return 0;
            }
            return profit;
        }
    },
    BUST {
        @Override
        int calculateProfit(Gamer gamer, Dealer dealer) {
            return gamer.profit() - gamer.getBettingMoney();
        }
    },
    STAY {
        @Override
        int calculateProfit(Gamer gamer, Dealer dealer) {
            int profit = gamer.profit();
            if (dealer.isBlackjack()) {
                return profit * (-1);
            }
            if (dealer.isBust()) {
                return profit;
            }
            return profit * Integer.compare(gamer.calculateScore(), dealer.calculateScore());
        }
    };

    abstract int calculateProfit(Gamer gamer, Dealer dealer);

    public static int profit(Gamer gamer, Dealer dealer) {
        if (gamer.isBlackjack()) {
            return BLACKJACK.calculateProfit(gamer, dealer);
        }
        if (gamer.isBust()) {
            return BUST.calculateProfit(gamer, dealer);
        }
        return STAY.calculateProfit(gamer, dealer);
    }
}
