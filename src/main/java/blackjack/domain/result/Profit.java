package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Profit {

    public Map<Player, Money> makePlayersProfit(final Dealer dealer, final Players players) {
        Map<Player, Money> profit = initProfit(players);

        for (Player player : players.getPlayers()) {
            changePlayersProfit(profit, dealer, player);
        }
        return profit;
    }

    private Map<Player, Money> initProfit(final Players players) {
        Map<Player, Money> profit = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            profit.put(player, Money.init());
        }
        return profit;
    }

    private void changePlayersProfit(final Map<Player, Money> profit, final Dealer dealer, final Player player) {
        earnMoneyIfDealerBust(profit, dealer, player);
        earnMoneyIfBlackjack(profit, dealer, player);
        loseMoneyIfPlayerBust(profit, dealer, player);
        compareScore(profit, dealer, player);
    }

    private void earnMoneyIfDealerBust(final Map<Player, Money> profit, final Dealer dealer, Player player) {
        if (dealer.isBust()) {
            profit.put(player, player.earnMoneyFromBet());
        }
    }

    private void earnMoneyIfBlackjack(final Map<Player, Money> profit, final Dealer dealer, Player player) {
        if (dealer.isBust()) {
            return;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            profit.put(player, player.earnMoneyFromBet());
            return;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            profit.put(player, player.earnMoneyFromBlackjack());
        }
    }

    private void loseMoneyIfPlayerBust(final Map<Player, Money> profit, final Dealer dealer, Player player) {
        if (dealer.isBust() || player.isBlackjack()) {
            return;
        }
        if (player.isBust()) {
            profit.put(player, player.loseMoneyFromBet());
        }
    }

    private void compareScore(final Map<Player, Money> profit, final Dealer dealer, final Player player) {
        if (dealer.isBust() || player.isBlackjack() || player.isBust()) {
            return;
        }

        Result result = player.decideResultAgainst(dealer);
        if (result == Result.WIN) {
            profit.put(player, player.earnMoneyFromBet());
            return;
        }
        if (result == Result.DRAW) {
            return;
        }
        profit.put(player, player.loseMoneyFromBet());
    }

    public Money getDealerProfit(final Map<Player, Money> playersProfit) {
        int dealerProfit = 0;

        for (Money money : playersProfit.values()) {
            dealerProfit -= money.getMoney();
        }

        return new Money(dealerProfit);
    }
}
