package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum ProfitWeight {
    NORMAL_PROFIT_WEIGHT(1.0),
    BLACKJACK_PROFIT_WEIGHT(1.5),
    LOSS_WEIGHT(-1.0);

    private final double weight;

    ProfitWeight(double weight) {
        this.weight = weight;
    }

    public static ProfitWeight findProfitWeight(Dealer dealer, Player player) {
        if (dealer.isBust()) {
            return calculateWhenDealerBust(player);
        }
        if (dealer.isBlackJack()) {
            return calculateWhenDealerBlackJack(player);
        }
        return calculateWhenNormalSituation(dealer, player);

    }

    private static ProfitWeight calculateWhenDealerBust(Player player) {
        if (player.isBust()) {
            return LOSS_WEIGHT;
        }
        return NORMAL_PROFIT_WEIGHT;
    }

    private static ProfitWeight calculateWhenDealerBlackJack(Player player) {
        if (player.isBlackJack()) {
            return NORMAL_PROFIT_WEIGHT;
        }
        return LOSS_WEIGHT;
    }

    private static ProfitWeight calculateWhenNormalSituation(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSS_WEIGHT;
        }
        if (player.isBlackJack()) {
            return BLACKJACK_PROFIT_WEIGHT;
        }
        if (player.calculateFinalScore() > dealer.calculateFinalScore()) {
            return NORMAL_PROFIT_WEIGHT;
        }
        return LOSS_WEIGHT;
    }

    public double getWeight() {
        return weight;
    }
}
