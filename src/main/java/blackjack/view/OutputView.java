package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printDealToAll(Participants participants) {
        System.out.println(messageResolver.resolveDealDescriptionMessage(participants.getPlayers()));
        System.out.println(messageResolver.resolveDealToAllMessage(participants));
    }

    public void printDrawToPlayer(Player player) {
        System.out.println(messageResolver.resolveParticipantHandMessage(player));
    }

    public void printLineSeparator() {
        System.out.println();
    }

    public void printDrawToDealer() {
        System.out.println(messageResolver.resolveDrawToDealerMessage());
    }

    public void printParticipantsHandScore(Participants participants) {
        System.out.println(messageResolver.resolveParticipantsHandScoreMessage(participants));
    }

    public void printParticipantsResult(Dealer dealer, Players players) {
        System.out.println("## 최종 승패");
        System.out.println(messageResolver.resolveDealerResult(dealer.judge(players)));
        System.out.println(messageResolver.resolvePlayersResult(players, dealer));
    }
}
