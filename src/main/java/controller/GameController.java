package controller;

import domain.BlackJackAction;
import domain.Deck;
import domain.Game;
import domain.GameResult;
import domain.user.Participant;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {
    
    public void run(Deck deck) {
        deck.shuffle();
        Game game = new Game(InputView.readPlayerNames(), deck);
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
            boolean isHit = InputView.readAnswerForMoreCard(currentParticipant.getName());
            game.play(currentParticipant, BlackJackAction.of(isHit));
            OutputView.printNameAndCards(currentParticipant.getName(), currentParticipant.getCards());
            currentParticipant = game.getCurrentParticipant();
        }
        while (game.isDealerNeedCard(currentParticipant)) {
            OutputView.printDealerReceivedCard();
            game.play(currentParticipant, BlackJackAction.HIT);
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
