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
        ParticipantNames participantNames = getParticipantNames();
        Map<ParticipantName, BettingAmount> bettingAmounts = getBettingAmounts(participantNames);
        gameManager = new GameManager(participantNames, bettingAmounts, new Deck());
    }

    private ParticipantNames getParticipantNames() {
        List<String> playerNames = InputView.readPlayerNames();
        return new ParticipantNames(playerNames.stream()
                .map(ParticipantName::new)
                .toList());
    }

    private Map<ParticipantName, BettingAmount> getBettingAmounts(ParticipantNames playerNames) {
        return playerNames.getParticipantNames().stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new BettingAmount(InputView.readBettingAmount(name.getName())),
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

        while (player.shouldHit()) {
            answer = InputView.askForOneMoreCard(player.getParticipantName());
            gameManager.drawCardForPlayer(player, answer);
            OutputView.printPlayerCard(player);
            if (!answer) {
                break;
            }
        }
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
