package contoller;

import domain.card.Deck;
import domain.GameManager;
import domain.participant.Participant;
import domain.GameResult;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        drawInitialCards();
        drawMorePlayersCards();
        drawMoreDealerCards();
        printParticipantsCards();
        printGameResult();
    }

    private void readyGame() {
        List<String> playerNames = InputView.readPlayerNames();
        Map<String, Integer> bettingAmounts = getBettingAmounts(playerNames);
        gameManager = new GameManager(playerNames, bettingAmounts, new Deck());
    }

    private Map<String, Integer> getBettingAmounts(List<String> playerNames) {
        Map<String, Integer> bettingAmounts = new HashMap<>();
        for (String name : playerNames) {
            int bettingAmount = InputView.readBettingAmount(name);
            bettingAmounts.put(name, bettingAmount);
        }
        return bettingAmounts;
    }

    private void drawInitialCards() {
        Participants participants = gameManager.findParticipants();
        OutputView.printInitialParticipants(participants);
    }

    private void drawMorePlayersCards() {
        Participants participants = gameManager.findParticipants();
        List<Participant> players = participants.findPlayers();
        for (Participant player : players) {
            drawCard(player);
        }
    }

    private void drawCard(Participant player) {
        boolean answer;
        boolean isFirstTurn = true;
        do {
            answer = InputView.askForOneMoreCard(player);
            drawCardIfAnswerIsYes(player, answer);

            printCardsIfFirstTurn(player, isFirstTurn);

            isFirstTurn = false;
        } while (gameManager.shouldPlayerHit(player) && answer);
    }

    private void drawCardIfAnswerIsYes(Participant player, boolean answer) {
        if (answer) {
            gameManager.drawCard(player);
        }
    }

    private static void printCardsIfFirstTurn(Participant player, boolean isFirstTurn) {
        if (isFirstTurn) {
            OutputView.printPlayerCard(player);
        }
    }

    private void drawMoreDealerCards() {
        while (gameManager.shouldDealerHit()) {
            OutputView.printDealerDrawMessage();
        }
    }

    private void printParticipantsCards() {
        Participants participants = gameManager.findParticipants();
        OutputView.printFinalParticipant(participants);
    }

    private void printGameResult() {
        Map<Participant, GameResult> result = gameManager.findGameResult();
        OutputView.printGameResult(result);
    }
}
