package blackjack.view;

import blackjack.domain.game.Judge;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Player2;
import blackjack.domain.participant.Players2;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printDealToAll(Participants participants) {
        System.out.println(messageResolver.resolveDealDescriptionMessage(participants.getPlayers()));
        System.out.println(messageResolver.resolveDealToAllMessage(participants));
    }

    public void printDealToAll(Dealer2 dealer, Players2 players) {
        System.out.println(messageResolver.resolveDealDescriptionMessage(players));
        System.out.println(messageResolver.resolveDealToDealerMessage(dealer));
        System.out.println(messageResolver.resolveDealToPlayersMessage(players));
    }

    public void printDrawToPlayer(Player2 player) {
        System.out.println(messageResolver.resolveDrawToPlayerMessage(player));
    }

    public void printDrawToDealer() {
        System.out.println(messageResolver.resolveDrawToDealerMessage());
    }

    public void printDrawToPlayer(Player player) {
        System.out.println(messageResolver.resolveParticipantHandMessage(player));
    }

    public void printLineSeparator() {
        System.out.println();
    }

    public void printParticipantsHandScore(Participants participants) {
        System.out.println(messageResolver.resolveParticipantsHandScoreMessage(participants));
    }

    public void printParticipantsHandScore(Dealer2 dealer, Players2 players) {
        System.out.println(messageResolver.resolveDealerHandScoreMessage(dealer));
        System.out.println(messageResolver.resolvePlayersHandScoreMessage(players));
    }

    public void printParticipantsResult(Judge judge) {
        System.out.println(messageResolver.resolveResultDescriptionMessage());
        System.out.println(messageResolver.resolveDealerResultMessage(judge));
        System.out.println(messageResolver.resolvePlayersResultMessage(judge));
    }

    public void printResult(PlayersResult playersResult) {
        System.out.println(messageResolver.resolveResultDescriptionMessage());
        System.out.println(messageResolver.resolveDealerResultMessage(playersResult));
        System.out.println(messageResolver.resolvePlayersResultMessage(playersResult));
    }
}
