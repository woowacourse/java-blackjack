package contoller;

import domain.card.Deck;
import domain.GameManager;
import domain.participant.Participant;
import domain.ResultStatus;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        drawPlayersCards();
        drawDealerCards();
        printParticipantsCards();
        printGameResult();
    }

    private void readyGame() {
        List<String> playerNames = InputView.readPlayerNames();
        gameManager = new GameManager(playerNames, new Deck());

        Participants participants = gameManager.findParticipants();
        OutputView.printInitialParticipants(participants);
    }

    private void drawPlayersCards() {
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

    private void drawDealerCards() {
        while (gameManager.shouldDealerHit()) {
            OutputView.printDealerDrawMessage();
        }
    }

    private void printParticipantsCards() {
        Participants participants = gameManager.findParticipants();
        OutputView.printFinalParticipant(participants);
    }

    private void printGameResult() {
        Map<Participant, ResultStatus> result = gameManager.findGameResult();
        OutputView.printGameResult(result);
    }
}
