package blackjack.domain.result;

import blackjack.domain.playing.user.Player;
import blackjack.domain.playing.user.User;

public class Profit {
    private int profit;

    public Profit(int profit) {
        this.profit = profit;
    }

    public static Profit of(Player player, User dealer) {
        BettingMoney bettingMoney = BettingMoneyRepository.findBy(player);
        double profitRate = ProfitRate.of(player, dealer);

        return new Profit(bettingMoney.multiplyAndGetValue(profitRate));
    }

    public int getProfit() {
        return profit;
    }
}
