package blackjack.view;

import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;

import java.util.List;

public class OutputView {
    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printInitialHandOfEachPlayer(Dealer dealer, List<Player> players) {
        System.out.println(messageResolver.resolveInitialHand(dealer, players));
    }

    public void printPlayerCard(Player player) {
        System.out.println(messageResolver.resolveCard(player));
    }

    public void printDealerHitMessage(Dealer dealer) {
        System.out.println(messageResolver.resolveDealerHitMessage(dealer));
    }

    public void printParticipantCardWithScore(Participant participant) {
        System.out.println(messageResolver.resolveParticipantCardWithScore(participant));
    }

    public void printPlayerProfit(ProfitDetails profits) {
        System.out.println(messageResolver.resolvePlayersProfitDetail(profits));
    }
}
