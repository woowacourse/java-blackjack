package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.Players;
import java.util.List;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printDealToAll(Dealer dealer, List<Player> players) {
        System.out.println(messageResolver.resolveDealDescriptionMessage(players));
        System.out.println(messageResolver.resolveDealerHandAfterDealMessage(dealer));
        System.out.println(messageResolver.resolvePlayersHandMessage(players));
    }

    public void printDrawToPlayer(Player player) {
        System.out.println(messageResolver.resolvePlayerHandMessage(player));
    }

    public void printDrawToDealer() {
        System.out.println(messageResolver.resolveDrawToDealerDescriptionMessage());
    }

    public void printLineSeparator() {
        System.out.println();
    }

    public void printAllHandScore(Dealer dealer, List<Player> players) {
        System.out.println(messageResolver.resolveDealerHandScoreMessage(dealer));
        System.out.println(messageResolver.resolvePlayersHandScoreMessage(players));
    }

    public void printAllProfit(Players players) {
        System.out.println(messageResolver.resolveProfitDescriptionMessage());
        System.out.println(messageResolver.resolveDealerProfitMessage(players));
        System.out.println(messageResolver.resolvePlayersProfitMessage(players));
    }
}
