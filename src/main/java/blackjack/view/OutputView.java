package blackjack.view;

import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.game.Judge;

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

    public void printParticipantsResult(Judge judge) {
        System.out.println(messageResolver.resolveResultDescriptionMessage());
        System.out.println(messageResolver.resolveDealerResultMessage(judge));
        System.out.println(messageResolver.resolvePlayersResultMessage(judge));
    }
}
