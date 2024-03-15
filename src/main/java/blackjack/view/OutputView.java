package blackjack.view;

import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;

public class OutputView {
    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printInitialHandOfEachPlayer(Dealer dealer, List<Player> players) {
        System.out.println(messageResolver.resolveInitialHandOfEachPlayer(dealer, players));
    }

    public void printPlayerCard(Player player) {
        System.out.println(messageResolver.resolvePlayerCard(player));
    }

    public void printDealerHitMessage(Dealer dealer) {
        System.out.println(messageResolver.resolveDealerHitMessage(dealer));
    }

    public void printPlayerCardWithScore(Player player) {
        System.out.println(messageResolver.resolvePlayerCardWithScore(player));
    }

    public void printPlayerProfit(ProfitDetails profits) {
        System.out.println(messageResolver.resolvePlayersProfitDetail(profits));
    }
}
