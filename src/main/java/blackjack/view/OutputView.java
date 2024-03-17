package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.PlayerProfits;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printParticipantInitialHand(Dealer dealer, Players players) {
        System.out.println(messageResolver.resolveInitialHandsMessage(dealer, players));
    }

    public void printPlayerHand(Player player) {
        System.out.println(messageResolver.resolvePlayerHandMessage(player));
    }

    public void printCompletedHandsStatus(Dealer dealer, Players players) {
        System.out.println(messageResolver.resolveCompletedHandsMessage(dealer, players));
    }

    public void printProfitResults(PlayerProfits playerProfits) {
        System.out.println(messageResolver.resolveProfitResultMessage(playerProfits));
    }
}
