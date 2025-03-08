package contoller;

import domain.Dealer;
import domain.Deck;
import domain.GameManager;
import domain.Player;
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
        this.gameManager = new GameManager(playerNames, new Deck());

        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printInitialParticipant(dealer, allPlayers);
    }

    private void drawPlayersCards() {
        List<Player> allPlayers = gameManager.findAllPlayers();
        for (Player player : allPlayers) {
            drawCard(player);
        }
    }

    private void drawCard(Player player) {
        boolean answer;
        boolean isFirstTurn = true;
        do {
            answer = InputView.askForOneMoreCard(player);
            player = drawAndCreateNewPlayer(player, answer);

            printCardsIfFirstTurn(player, isFirstTurn, answer);

            isFirstTurn = false;
        } while (!player.checkExceedTwentyOne() && answer);
    }

    private Player drawAndCreateNewPlayer(Player player, boolean answer) {
        if (answer) {
            player = gameManager.drawCard(player);
            gameManager.editPlayers(player);
        }
        return player;
    }

    private static void printCardsIfFirstTurn(Player player, boolean isFirstTurn, boolean answer) {
        if (isFirstTurn || answer) {
            OutputView.printPlayerCard(player);
        }
    }

    private void drawDealerCards() {
        Dealer dealer = gameManager.findDealer();
        while (!dealer.checkDealerNeedsMoreCard()) {
            dealer = gameManager.drawCard(dealer);
            OutputView.printDealerDrawMessage();
        }
    }

    private void printParticipantsCards() {
        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printFinalParticipant(dealer, allPlayers);
    }

    private void printGameResult() {
        Map<Player, ResultStatus> result = gameManager.findGameResult();
        OutputView.printGameResult(result);
    }
}
