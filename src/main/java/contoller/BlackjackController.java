package contoller;

import domain.Deck;
import domain.GameManager;
import domain.Participant;
import domain.ResultStatus;
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

        Participant dealer = gameManager.findDealer();
        List<Participant> players = gameManager.findPlayers();
        OutputView.printInitialParticipant(dealer, players);
    }

    private void drawPlayersCards() {
        List<Participant> players = gameManager.findPlayers();
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
        } while (!player.isBurst() && answer);
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
        Participant dealer = gameManager.findDealer();
        while (dealer.shouldHit()) {
            gameManager.drawCard(dealer);
            OutputView.printDealerDrawMessage();
        }
    }

    private void printParticipantsCards() {
        Participant dealer = gameManager.findDealer();
        List<Participant> players = gameManager.findPlayers();
        OutputView.printFinalParticipant(dealer, players);
    }

    private void printGameResult() {
        Map<Participant, ResultStatus> result = gameManager.findGameResult();
        OutputView.printGameResult(result);
    }
}
