package controller;

import domain.BlackJackAction;
import domain.Game;
import domain.GameResult;
import domain.user.Participant;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {
    
    public void run() {
        Game game = new Game(InputView.readPlayerNames());
        makeGameReady(game);
        playGame(game);
        printFinalGameResult(game);
    }
    
    private void makeGameReady(Game game) {
        game.ready();
        List<Participant> allParticipant = game.getAllParticipant();
        OutputView.printReadyMessage(allParticipant);
        OutputView.printParticipantsNameAndCards(allParticipant);
    }
    
    private void playGame(Game game) {
        Participant currentParticipant = game.getCurrentParticipant();
        while (currentParticipant.isPlayer()) {
            String actionInput = InputView.readAnswerForMoreCard(currentParticipant.getName());
            BlackJackAction action = BlackJackAction.of(actionInput);
            game.run(currentParticipant, action);
            OutputView.printNameAndCards(currentParticipant.getName(), currentParticipant.getCards());
            currentParticipant = game.getCurrentParticipant();
        }
        while (game.isDealerNeedCard(currentParticipant)) {
            OutputView.printDealerReceivedCard();
            game.run(currentParticipant, BlackJackAction.HIT);
        }
    }
    
    private void printFinalGameResult(Game game) {
        List<Participant> allPlayers = game.getAllParticipant();
        List<GameResult> finalGameResults = game.getFinalGameResults();
        OutputView.printAllParticipantsStatus(allPlayers);
        OutputView.printDealerGameResult(finalGameResults.get(0), finalGameResults.size() - 1);
        for (int i = 1; i < allPlayers.size(); i++) {
            OutputView.printPlayerResult(allPlayers.get(i).getName(), finalGameResults.get(i));
        }
    }
    
    
}
