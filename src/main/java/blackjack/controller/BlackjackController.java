package blackjack.controller;

import blackjack.domain.GameMachine;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play(final GameMachine gameMachine) {
        OutputView.printPlayersInitCardInfo(gameMachine.getPlayers());
        decideMoreCard(gameMachine);
        announcePlayersFinishInfo(gameMachine.getPlayers());
        competeWithDealer(gameMachine.getPlayers());
        OutputView.printResult(gameMachine.getPlayers());
    }

    private void decideMoreCard(final GameMachine gameMachine) {
        for (Player participant : gameMachine.getPlayers().getParticipants()) {
            decideParticipantOneMoreCard(participant, gameMachine);
        }
        decideOneMoreCard(gameMachine);
    }

    private void decideParticipantOneMoreCard(final Player participant, final GameMachine gameMachine) {
        while (isNotOverMaxScore(participant) && InputView.oneMoreCard(participant)) {
            participant.addCard(gameMachine.playDraw());
            OutputView.printPlayerCardInfo(participant);
        }
    }

    private boolean isNotOverMaxScore(final Player participant) {
        if (((Participant) participant).isOverMaxScore()) {
            OutputView.printParticipantOverMaxScore(participant.getName());
        }
        return !((Participant) participant).isOverMaxScore();
    }

    private void decideOneMoreCard(final GameMachine gameMachine) {
        if (gameMachine.isDealerGetCard()) {
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    private void competeWithDealer(final Players players) {
        players.getParticipants().forEach(player ->
                ((Dealer) players.getDealer()).compete((Participant) player));
    }

    private void announcePlayersFinishInfo(final Players players) {
        OutputView.printPlayerFinalInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }
}
