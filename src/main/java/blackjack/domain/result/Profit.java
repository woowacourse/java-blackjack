package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Profit {

    private final Participants participants;
    private Map<Player, Money> profit;

    public Profit(final Participants participants) {
        this.participants = participants;
        initProfit();
    }

    private void initProfit() {
        profit = new LinkedHashMap<>();
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

    private void changePlayersProfit(final Dealer dealer, final Player player) {
        if (hasState(dealer, player)) {
            return;
        }
        compareScore(dealer.calculateTotalScore(), player);
    }

    private boolean hasState(final Dealer dealer, final Player player) {
        if (isPlayerBlackjack(dealer, player)) {
            return true;
        }
        return isBust(dealer, player);
    }

    private boolean isPlayerBlackjack(final Dealer dealer, final Player player) {
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

    private boolean isBust(final Dealer dealer, final Player player) {
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
