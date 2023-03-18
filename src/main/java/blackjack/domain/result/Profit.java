package blackjack.domain.result;

import blackjack.domain.participant.Money;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Profit {

    private final Dealer dealer;
    private final Players players;

    public Profit(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Player, Money> makePlayersProfit() {
        Map<Player, Money> profit = initProfit();

        for (Player player : players.getPlayers()) {
            changePlayersProfit(profit, player);
        }

        return profit;
    }

    private Map<Player, Money> initProfit() {
        Map<Player, Money> profit = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            profit.put(player, new Money(0));
        }

        return profit;
    }

    private void changePlayersProfit(final Map<Player, Money> profit, final Player player) {
        if (hasState(profit, player)) {
            return;
        }
        compareScore(profit, dealer.calculateTotalScore(), player);
    }

    private boolean hasState(final Map<Player, Money> profit, final Player player) {
        if (isPlayerBlackjack(profit, player)) {
            return true;
        }
        return isBust(profit, player);
    }

    private boolean isPlayerBlackjack(final Map<Player, Money> profit, final Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            profit.put(player, player.getBettingMoney().getBlackjackPrize());
            return true;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            profit.put(player, player.getBettingMoney().getBettingPrize());
            return true;
        }
        return false;
    }

    private boolean isBust(final Map<Player, Money> profit, final Player player) {
        if (player.isBust()) {
            profit.put(player, player.getBettingMoney().loseBettingPrize());
            return true;
        }
        if (dealer.isBust()) {
            profit.put(player, player.getBettingMoney().getBettingPrize());
            return true;
        }
        return false;
    }

    private void compareScore(final Map<Player, Money> profit, final Score dealerScore, final Player player) {
        if (dealerScore.isLose(player.calculateTotalScore())) {
            profit.put(player, player.getBettingMoney().getBettingPrize());
            return;
        }
        if (dealerScore.isDraw(player.calculateTotalScore())) {
            return;
        }
        profit.put(player, player.getBettingMoney().loseBettingPrize());
    }

    public Money getDealerProfit(final Map<Player, Money> playersProfit) {
        int dealerProfit = 0;

        for (Money money : playersProfit.values()) {
            dealerProfit += money.getMoney() * (-1);
        }

        return new Money(dealerProfit);
    }
}
