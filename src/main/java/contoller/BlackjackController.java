package contoller;

import domain.Dealer;
import domain.Deck;
import domain.GameManager;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        drawPlayersCards();
    }

    private void drawPlayersCards() {
        List<Player> allPlayers = gameManager.findAllPlayers();
        for (Player player : allPlayers) {
            boolean answer;
            boolean isFirstTurn = true;
            do {
                answer = InputView.askForOneMoreCard(player);
                if (answer) {
                    player = gameManager.drawCard(player);
                }

                if (isFirstTurn) {
                    OutputView.printPlayerCard(player);
                }

                isFirstTurn = false;
            } while (!player.checkExceedTwentyOne() && answer);
        }
    }

    private void readyGame() {
        List<String> playerNames = InputView.readPlayerNames();
        this.gameManager = new GameManager(playerNames, new Deck());

        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printInitialParticipant(dealer, allPlayers);
    }
}
