package contoller;

import domain.card.Deck;
import domain.GameManager;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        drawInitialCards();
        drawMorePlayersCards();
        drawMoreDealerCards();
        printParticipantsCards();
        printProfits();
    }

    private void readyGame() {
        List<String> playerNames = InputView.readPlayerNames();
        Map<String, Integer> bettingAmounts = getBettingAmounts(playerNames);
        gameManager = new GameManager(playerNames, bettingAmounts, new Deck());
    }

    private Map<String, Integer> getBettingAmounts(List<String> playerNames) {
        return playerNames.stream()
                .collect(Collectors.toMap(name -> name,
                        InputView::readBettingAmount
                ));
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

        while (gameManager.shouldPlayerHit(player)) {
            answer = InputView.askForOneMoreCard(player);
            drawCardIfAnswerIsYes(player, answer);
            if (!answer) {
                break;
            }
            OutputView.printPlayerCard(player);
        }
    }

    private void drawCardIfAnswerIsYes(Participant player, boolean answer) {
        if (answer) {
            gameManager.drawCard(player);
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

    private void printProfits() {
        Map<Participant, Integer> profits = gameManager.findPlayersProfits();
        int profitOfDealer = gameManager.findDealerProfit();
        OutputView.printProfits(profits, profitOfDealer);
    }
}
