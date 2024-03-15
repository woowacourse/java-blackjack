package blackjack.view;

import blackjack.domain.game.PlayersResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players2;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printDealToAll(Dealer dealer, Players2 players) {
        System.out.println(messageResolver.resolveDealDescriptionMessage(players));
        System.out.println(messageResolver.resolveDealToDealerMessage(dealer));
        System.out.println(messageResolver.resolveDealToPlayersMessage(players));
    }

    public void printDrawToPlayer(Player player) {
        System.out.println(messageResolver.resolveDrawToPlayerMessage(player));
    }

    public void printDrawToDealer() {
        System.out.println(messageResolver.resolveDrawToDealerMessage());
    }

    public void printLineSeparator() {
        System.out.println();
    }

    public void printParticipantsHandScore(Dealer dealer, Players2 players) {
        System.out.println(messageResolver.resolveDealerHandScoreMessage(dealer));
        System.out.println(messageResolver.resolvePlayersHandScoreMessage(players));
    }

    public void printResult(PlayersResult playersResult) {
        System.out.println(messageResolver.resolveResultDescriptionMessage());
        System.out.println(messageResolver.resolveDealerResultMessage(playersResult));
        System.out.println(messageResolver.resolvePlayersResultMessage(playersResult));
    }
}
