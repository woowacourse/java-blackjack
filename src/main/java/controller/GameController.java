package controller;

import domain.BlackJackAction;
import domain.Game;
import domain.GameResult;
import domain.user.Participant;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class GameController {

    public void run(){
        String participantNames = InputView.getParticipantNames();
        Game game = new Game(participantNames);
        makeGameReady(game);
        playGame(game);
        printFinalGameResult(game);
    }

    private void makeGameReady(Game game) {
        game.ready();
        List<Participant> allParticipant = game.getAllParticipant();
        OutputView.printReady(allParticipant.stream().map(Participant::getName).collect(Collectors.toList()));
        allParticipant.forEach(
            (participant) -> OutputView.printNameAndCards(participant.getName(), participant.getReadyCards()));
    }

    private void playGame(Game game) {
        Participant current = game.getCurrentParticipant();
        while (current.isPlayer()) {
            String input = InputView.inputNeedMoreCard(current.getName());
            BlackJackAction action = BlackJackAction.getActionByInput(input);
            game.run(current, action);
            OutputView.printNameAndCards(current.getName(), current.getCards());
            current = game.getCurrentParticipant();
        }
        while (game.isDealerNeedCard(current)) {
            OutputView.printDealerReceivedCard();
            game.run(current, BlackJackAction.HIT);
        }
    }

    private void printFinalGameResult(Game game) {
        List<Participant> allPlayers = game.getAllParticipant();
        printAllParticipantsStatus(allPlayers);
        List<GameResult> finalGameResults = game.getFinalGameResults();
        OutputView.printDealerGameResult(finalGameResults.get(0), finalGameResults.size() - 1);
        for (int i = 1; i < allPlayers.size(); i++) {
            OutputView.printPlayerResult(allPlayers.get(i).getName(), finalGameResults.get(i));
        }
    }

    private void printAllParticipantsStatus(List<Participant> allPlayers) {
        allPlayers.forEach(
            (participant) -> OutputView.printNameCardsScore(participant.getName(), participant.getCards(),
                participant.calculateScore()));
    }
}
