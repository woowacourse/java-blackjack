package controller;

import domain.BlackJackGame;
import domain.BoxResult;
import domain.TurnAction;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class GameController {

    private final BlackJackGame blackJackGame;

    public GameController(BlackJackGame game) {
        this.blackJackGame = game;
    }

    public void ready() {
        blackJackGame.dealInitialHand();
        List<Player> allParticipant = blackJackGame.getPlayersAndDealerAtFirst();
        OutputView.printReady(allParticipant.stream().map(Player::getName).collect(Collectors.toList()));
        allParticipant.forEach(
            (participant) -> OutputView.printNameAndHand(participant.getName(), participant.faceUpInitialHand()));
    }

    public void play() {
        Player current = blackJackGame.getCurrentPlayer();
        while (current.isPlayer()) {
            String input = InputView.inputNeedMoreCard(current.getName());
            TurnAction action = TurnAction.getActionByInput(input);
            blackJackGame.progressTurn(current, action);
            OutputView.printNameAndHand(current.getName(), current.showHand());
            current = blackJackGame.getCurrentPlayer();
        }
        while (blackJackGame.isDealerUnderThresholds(current)) {
            OutputView.printDealerReceivedCard();
            blackJackGame.progressTurn(current, TurnAction.HIT);
        }
    }

    public void printFinalGameResult() {
        List<Player> dealerAndPlayers = blackJackGame.getPlayersAndDealerAtFirst();
        printAllParticipantsStatus(dealerAndPlayers);
        List<BoxResult> gameResult = blackJackGame.showResult();
        OutputView.printDealerGameResult(gameResult.get(0), gameResult.size() - 1);
        for (int i = 1; i < dealerAndPlayers.size(); i++) {
            OutputView.printPlayerResult(dealerAndPlayers.get(i).getName(), gameResult.get(i));
        }
    }

    private void printAllParticipantsStatus(List<Player> allPlayers) {
        allPlayers.forEach(
            (participant) -> OutputView.printNameHandScore(participant.getName(), participant.showHand(),
                participant.calculatePoint()));
    }
}
