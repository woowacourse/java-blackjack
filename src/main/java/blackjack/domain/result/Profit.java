package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class Profit {

    private final Participants participants;
    private Map<Player, Money> profit;

    public Profit(Participants participants) {
        this.participants = participants;
        initProfit();
    }

    private void initProfit() {
        profit = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            profit.put(player, Money.init());
        }
    }

    public Map<Player, Money> makePlayersProfit() {
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            changePlayersProfit(dealer, player);
        }

        return profit;
    }

    private void changePlayersProfit(Dealer dealer, Player player) {
        if (hasState(dealer, player)) {
            return;
        }
        compareScore(dealer.calculateTotalScore(), player);
    }

    private boolean hasState(Dealer dealer, Player player) {
        if (isPlayerBlackjack(dealer, player)) {
            return true;
        }
        return isBust(dealer, player);
    }

    private boolean isPlayerBlackjack(Dealer dealer, Player player) {
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

    private boolean isBust(Dealer dealer, Player player) {
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

    private void compareScore(final Score dealerScore, final Player player) {
        if (dealerScore.isLess(player.calculateTotalScore())) {
            profit.put(player, player.getBettingMoney().getBettingPrize());
            return;
        }
        profit.put(player, player.getBettingMoney().loseBettingPrize());
    }

    public Money getDealerProfit(Map<Player, Money> playersProfit) {
        int dealerProfit = 0;

        for (Money money : playersProfit.values()) {
            dealerProfit += money.getMoney() * (-1);
        }

        return new Money(dealerProfit);
    }
}
