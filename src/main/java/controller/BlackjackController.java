package controller;

import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Player;
import domain.participant.Players;
import domain.game.Referee;
import domain.game.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputView.inputPlayers();
        Players players = new Players(inputBets(names));
        Dealer dealer = new Dealer("딜러");
        Deck deck = new Deck();
        dealInitialTwoCards(dealer, players, deck);
        printInitialState(dealer, players, names);
        playAllPlayerTurns(players, deck);
        playDealerTurn(dealer, deck);
        printFinalCards(dealer, players);
        printProfitResult(dealer, players);
    }

    private void playAllPlayerTurns(Players players, Deck deck) {
        for (Player player : players.getGamePlayers()) {
            playPlayerTurn(player, deck);
        }
    }

    private Map<String, Integer> inputBets(List<String> names) {
        Map<String, Integer> nameToBet = new LinkedHashMap<>();
        for (String name : names) {
            nameToBet.put(name, inputView.inputBettingAmount(name));
        }
        return nameToBet;
    }

    private void dealInitialTwoCards(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.getGamePlayers()) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }

    private void printInitialState(Dealer dealer, Players players, List<String> names) {
        outputView.printInitialDeal(names);
        outputView.printDealerInitialCard(dealer.getInitialCard());
        for (Player player : players.getGamePlayers()) {
            outputView.printPlayerCards(player);
        }
        outputView.printNewLine();
    }

    private void playPlayerTurn(Player player, Deck deck) {
        while (player.canHit() && inputView.askHit(player.getName())) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.addCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        outputView.printNewLine();
        outputView.printFinalCards(dealer);
        for (Player player : players.getGamePlayers()) {
            outputView.printFinalCards(player);
        }
    }

    private Map<Player, Integer> calculateProfits(Dealer dealer, Players players) {
        Referee referee = new Referee();
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        for (Player player : players.getGamePlayers()) {
            Result result = referee.judge(player, dealer);
            int profit = player.calculateProfit(result);
            playerProfits.put(player, profit);
        }
        return playerProfits;
    }

    private void printProfitResult(Dealer dealer, Players players) {
        Map<Player, Integer> playerProfits = calculateProfits(dealer, players);
        int dealerProfit = 0;
        for (int profit : playerProfits.values()) {
            dealerProfit -= profit;
        }
        outputView.printProfitResult(dealer.getName(), dealerProfit, playerProfits);
    }
}
