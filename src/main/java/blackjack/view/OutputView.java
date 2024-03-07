package blackjack.view;

import blackjack.domain.Player;
import blackjack.domain.Players;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printHandOutEvent(Players players, int handedCount) {
        System.out.println(messageResolver.resolveHandOutEventMessage(players, handedCount));
    }

    public void printPlayerHand(Player player) {
        System.out.println(messageResolver.resolvePlayerHandMessage(player));
    }

    public void printDealerPopCount(int dealerPopThreshold, int count) {
        System.out.println(messageResolver.resolveDealerPopCountMessage(dealerPopThreshold, count));
    }

    public void printPlayersScore(Players players) {
        System.out.println(messageResolver.resolvePlayersScoreMessage(players));
    }
}
