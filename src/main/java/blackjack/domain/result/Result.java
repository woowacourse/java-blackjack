package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Result {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0);

    private final double profit;

    Result(final double profit) {
        this.profit = profit;
    }

    public double findProfitRate(final Dealer dealer, final Player player) {
        if (dealer.isBust()) {
            return calculateWhenDealerBust(player);
        }
        if (dealer.isBlackjack()) {
            return calculateWhenDealerBlackjack(player);
        }
        return calculateOtherCase(dealer, player);
    }

    private double calculateWhenDealerBust(final Player player) {
        if (player.isBust()) {
            return LOSE.getProfit();
        }
        if (player.isBlackjack()) {
            return BLACKJACK.getProfit();
        }
        return WIN.getProfit();
    }

    private double calculateWhenDealerBlackjack(final Player player) {
        if (player.isBlackjack()) {
            return DRAW.getProfit();
        }
        return LOSE.getProfit();
    }

    private double calculateOtherCase(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE.getProfit();
        }
        if (player.isBlackjack()) {
            return BLACKJACK.getProfit();
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return WIN.getProfit();
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return DRAW.getProfit();
        }
        return LOSE.getProfit();
    }

    private double getProfit() {
        return profit;
    }
}
