package controller;

import domain.BettingAmount;
import domain.GameManager;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.ParticipantName;
import domain.participant.ParticipantNames;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private GameManager gameManager;

    public void run() {
        readyGame();
        printInitialCards();
        drawMorePlayersCards();
        drawMoreDealerCards();
        printFinalCards();
        printProfits();
    }

    private void readyGame() {
        ParticipantNames playerNames = getParticipantNames();
        Map<ParticipantName, BettingAmount> bettingAmounts = getBettingAmounts(playerNames);
        gameManager = new GameManager(playerNames, bettingAmounts, new Deck());
    }

    private ParticipantNames getParticipantNames() {
        List<String> playerNames = InputView.readPlayerNames();
        return new ParticipantNames(playerNames.stream()
                .map(ParticipantName::new)
                .toList());
    }

    private Map<ParticipantName, BettingAmount> getBettingAmounts(ParticipantNames playerNames) {
        return playerNames.participantNames().stream()
                .collect(Collectors.toMap(
                        playerName -> playerName,
                        playerName -> new BettingAmount(InputView.readBettingAmount(playerName.name())),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private void printInitialCards() {
        Dealer dealer = gameManager.getDealer();
        List<Player> players = gameManager.getPlayers();
        OutputView.printInitialCards(dealer, players);
    }

    private void drawMorePlayersCards() {
        List<Player> players = gameManager.getPlayers();
        for (Player player : players) {
            drawCard(player);
        }
    }

    private void drawCard(Player player) {
        boolean answer;

        do {
            answer = InputView.askForOneMoreCard(player.getParticipantName());
            gameManager.drawCardForPlayer(player, answer);
            OutputView.printPlayerCard(player);
        } while (player.shouldHit() && answer);
    }

    private void drawMoreDealerCards() {
        while (gameManager.shouldDealerHit()) {
            gameManager.drawCardForDealer();
            OutputView.printDealerDrawMessage();
        }
    }

    private void printFinalCards() {
        Dealer dealer = gameManager.getDealer();
        List<Player> players = gameManager.getPlayers();
        OutputView.printFinalCards(dealer, players);
    }

    private void printProfits() {
        Map<Player, Integer> profits = gameManager.findPlayersProfits();
        int profitOfDealer = gameManager.findDealerProfit();
        OutputView.printProfits(profits, profitOfDealer);
    }
}
