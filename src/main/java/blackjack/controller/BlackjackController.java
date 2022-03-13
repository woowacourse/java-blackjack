package blackjack.controller;

import blackjack.domain.GameMachine;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public GameMachine createGameMachine() {
        try {
            List<String> names = InputView.responseNames();
            return new GameMachine(names);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createGameMachine();
        }
    }

    public void play(final GameMachine gameMachine) {
        OutputView.printPlayersInitCardInfo(gameMachine.getPlayers());
        decideMoreCard(gameMachine);
        announcePlayersFinishInfo(gameMachine.getPlayers());
        compete(gameMachine.getPlayers());
        OutputView.printResult(gameMachine.getPlayers());
    }

    private void decideMoreCard(final GameMachine gameMachine) {
        for (Player participant : gameMachine.getParicipants()) {
            fulfilParticipantOneMoreCard(participant, gameMachine);
        }
        decideOneMoreCard(gameMachine);
    }

    private void fulfilParticipantOneMoreCard(Player participant, GameMachine gameMachine) {
        try {
            decideParticipantOneMoreCard(participant, gameMachine);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            fulfilParticipantOneMoreCard(participant, gameMachine);
        }
    }

    private void decideParticipantOneMoreCard(final Player participant, final GameMachine gameMachine) {
        while (isNotOverMaxScore(participant) && InputView.oneMoreCard(participant)) {
            participant.addCard(gameMachine.playDraw());
            OutputView.printPlayerCardInfo(participant);
        }
    }

    private boolean isNotOverMaxScore(final Player participant) {
        if (!participant.acceptableCard()) {
            OutputView.printParticipantOverMaxScore(participant.getName());
        }
        return participant.acceptableCard();
    }

    private void decideOneMoreCard(final GameMachine gameMachine) {
        if (gameMachine.isDealerGetCard()) {
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    private void compete(final Players players) {
        players.competeWithDealer();
    }

    private void announcePlayersFinishInfo(final Players players) {
        OutputView.printPlayerFinalInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }
}
