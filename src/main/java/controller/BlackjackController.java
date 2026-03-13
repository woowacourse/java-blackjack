package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.Referee;
import domain.Result;
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
        List<Integer> betAmounts = inputView.inputBetAmount(names);
        Players players = new Players(names,betAmounts);
        Dealer dealer = new Dealer("딜러");
        Deck deck = new Deck();
        dealInitialCards(dealer, players, deck);
        dealInitialCards(dealer, players, deck);
        printInitialState(dealer, players, names);
        playAllPlayerTurns(players, deck);
        playDealerTurn(dealer, deck);
        printFinalState(dealer, players);
        printResult(dealer, players);
        printProfit(dealer, players);
    }

    private void playAllPlayerTurns(Players players, Deck deck) {
        for (Player player : players.getGamePlayers()) {
            playPlayerTurn(player, deck);
        }
    }

    private void dealInitialCards(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getGamePlayers()) {
            player.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
    }

    private void printInitialState(Dealer dealer, Players players, List<String> names) {
        outputView.printInitialDeal(names);
        outputView.printDealerInitialCard(dealer.getInitialCard());
        for (Player player : players.getGamePlayers()) {
            outputView.printPlayerCards(player);
        }
        System.out.println();
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

    private void printFinalState(Dealer dealer, Players players) {
        System.out.println();
        outputView.printFinalCards(dealer);
        for (Player player : players.getGamePlayers()) {
            outputView.printFinalCards(player);
        }
    }

    private void printResult(Dealer dealer, Players players) {
        Referee referee = new Referee();
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players.getGamePlayers()) {
            results.put(player, referee.judge(player.getScore(), dealer.getScore()));
        }
        outputView.printFinalResult(dealer, results);
    }

    private void printProfit(Dealer dealer, Players players) {
        Referee referee = new Referee();
        Map<Player, Integer> profit = new LinkedHashMap<>();
        double dealerProfit = 0;
        for (Player player : players.getGamePlayers()) {
            Result result = referee.judge(player.getScore(), dealer.getScore());
            int playerProfit = (int) referee.calculateProfit(result, player.getBetAmount());
            dealerProfit -= playerProfit;
            profit.put(player, playerProfit);
        }
        outputView.printFinalProfit(dealer, dealerProfit, profit);
    }
}
