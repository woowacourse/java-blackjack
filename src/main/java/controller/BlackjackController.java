package controller;

import domain.participant.Dealer;
import domain.card.Deck;
import domain.GameManager;
import domain.participant.Player;
import domain.dto.PlayerInfo;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        drawPlayersCards();
        drawDealerCards();
        printParticipantsCards();
        printIncomes();
    }

    private void readyGame() {
        List<String> playerNames = InputView.readPlayerNames();
        List<PlayerInfo> playerInfos = readPlayerBetAmount(playerNames);
        this.gameManager = new GameManager(playerInfos, new Deck());

        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printInitialParticipant(dealer, allPlayers);
    }


    private static List<PlayerInfo> readPlayerBetAmount(List<String> playerNames) {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        for (String playerName : playerNames) {
            playerInfos.add(new PlayerInfo(playerName, InputView.readBetAmount(playerName)));
        }
        return playerInfos;
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

            drawPlayerCards(player, answer);

            printCardsIfFirstTurn(player, isFirstTurn, answer);

            isFirstTurn = false;
        } while (!gameManager.isPlayerBurst(player) && answer);
    }

    private void drawPlayerCards(Player player, boolean answer) {
        if (answer) {
            gameManager.drawCard(player);
        }
    }

    private static void printCardsIfFirstTurn(Player player, boolean isFirstTurn, boolean answer) {
        if (isFirstTurn || answer) {
            OutputView.printPlayerCard(player);
        }
    }

    private void drawDealerCards() {
        Dealer dealer = gameManager.findDealer();
        while (gameManager.isDealerHittable(dealer)) {
            gameManager.drawCard(dealer);
            OutputView.printDealerDrawMessage();
        }
    }

    private void printParticipantsCards() {
        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printFinalParticipant(dealer, allPlayers);
    }

    private void printIncomes() {
        Map<Player, Integer> incomes = gameManager.calculateIncomes();
        OutputView.printGameResult(incomes);
    }
}
