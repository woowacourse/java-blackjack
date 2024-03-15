package blackjack.view;

import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;

public class OutputView {
    private final MessageResolver messageResolver;

    public OutputView(final MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printInitialHandOfEachPlayer(final Dealer dealer, final List<Player> players) {
        System.out.println(messageResolver.resolveInitialHandOfEachPlayer(dealer, players));
    }

    public void printPlayerCard(final Player player) {
        System.out.println(messageResolver.resolvePlayerCard(player));
    }

    public void printDealerHitMessage(final Dealer dealer) {
        System.out.println(messageResolver.resolveDealerHitMessage(dealer));
    }

    public void printPlayerCardWithScore(final Player player) {
        System.out.println(messageResolver.resolvePlayerCardWithScore(player));
    }

    public void printPlayerProfit(final ProfitDetails profits) {
        System.out.println(messageResolver.resolvePlayersProfitDetail(profits));
    }
}
