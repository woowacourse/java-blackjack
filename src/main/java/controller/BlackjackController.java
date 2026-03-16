package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.Profit;
import domain.Profits;
import domain.Referee;
import domain.Result;
import domain.Results;
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
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        Referee referee = new Referee();
        Results results = new Results();
        dealInitialCards(dealer, players, deck);
        dealInitialCards(dealer, players, deck);
        printInitialState(dealer, players, names);
        playAllPlayerTurns(players, deck);
        playDealerTurn(dealer, deck);
        printFinalState(dealer, players);
        printResult(referee,results,dealer,players);
        printProfit(referee, players, results, dealer);
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

    private void playAllPlayerTurns(Players players, Deck deck) {
        for (Player player : players.getGamePlayers()) {
            playPlayerTurn(player, deck);
        }
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

    private void printResult(Referee referee, Results results, Dealer dealer, Players players) {
        for (Player player : players.getGamePlayers()) {
            results.addResult(player, referee.judge(player, dealer));
        }
        outputView.printFinalResult(dealer, results);
    }

    private void printProfit(Referee referee,Players players, Results results, Dealer dealer) {
        Profits profits = new Profits(referee, results);
        List<Profit> playerProfit = profits.getProfits();
        Profit dealerProfit = profits.getDealerProfit();
        outputView.printFinalProfit(dealer, players, results, profits, dealerProfit);
    }
}
