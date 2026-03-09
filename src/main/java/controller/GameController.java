package controller;

import domain.Dealer;
import domain.Deck;
import domain.GameResult;
import domain.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = getPlayerNames();

        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.handOutCards());

        List<Player> players = getPlayers(playerNames, deck);
        printGameStart(playerNames, dealer, players);
        receiveMoreCard(players, deck, dealer);

        outputView.printFinalScore(dealer, players);

        Map<String, GameResult> playerFinalResults = getPlayerFinalResults(players, dealer);
        outputView.printPlayerFinalResults(playerFinalResults);
    }

    private List<String> getPlayerNames() {
        String rawPlayerNames = inputView.readPlayerName();
        return InputParser.parsePlayerNames(rawPlayerNames);
    }

    private List<Player> getPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName, deck.handOutCards());
            players.add(player);
        }
        return players;
    }

    private void printGameStart(List<String> playerNames, Dealer dealer, List<Player> players) {
        outputView.printStartCardMessage(playerNames);
        outputView.printDealerStartCard(dealer.getHoldCards().getFirst());
        outputView.printStartCard(players);
    }

    private void receiveMoreCard(List<Player> players, Deck deck, Dealer dealer) {
        for (Player player : players) {
            processRound(player, deck);
        }

        while (dealer.isReceiveCard()) {
            dealer.addCard(deck.peekCard());
            outputView.printDealerReceiveCard();
        }
    }

    private Map<String, GameResult> getPlayerFinalResults(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerFinalResults = new HashMap<>();
        int dealerWinningCount = 0;
        int dealerLosingCount = 0;

        for (Player player : players) {
            GameResult gameResult = player.compareScore(dealer.calculateTotalScore());
            playerFinalResults.put(player.getName(), gameResult);

            if (gameResult == GameResult.WIN) {
                dealerLosingCount += 1;
            }
            if (gameResult == GameResult.LOSE) {
                dealerWinningCount += 1;
            }
        }
        outputView.printDealerFinalCount(dealerWinningCount, dealerLosingCount);
        return playerFinalResults;
    }

    private void processRound(Player player, Deck deck) {
        String hitOption = inputView.readHitOption(player.getName());
        if (hitOption.equals("y")) {
            player.addCard(deck.peekCard());
        }
        outputView.printCurrentHoldCard(player);

        while (hitOption.equals("y") && !player.isBust()) {
            hitOption = inputView.readHitOption(player.getName());
            if (hitOption.equals("n")) {
                break;
            }
            player.addCard(deck.peekCard());
            outputView.printCurrentHoldCard(player);
        }
    }
}
