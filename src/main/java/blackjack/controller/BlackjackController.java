package blackjack.controller;

import blackjack.domain.GameMachine;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;

public final class BlackjackController {

    public GameMachine createGameMachine() {
        try {
            List<String> names = InputView.responseNames();
            HashMap<String, Bet> bets = createBets(names);
            return new GameMachine(names, bets);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createGameMachine();
        }
    }

    private HashMap<String, Bet> createBets(List<String> names) {
        HashMap<String, Bet> bets = new HashMap<>();
        for (String name : names) {
            bets.put(name, InputView.responseBetAmount(name));
        }
        return bets;
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
        if (gameMachine.isDealerGetAdditionalCard()) {
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
