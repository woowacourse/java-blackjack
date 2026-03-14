package controller;

import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Player;
import domain.participant.Players;
import domain.game.BlackjackRule;
import domain.game.Outcome;
import domain.game.ProfitResult;
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

    private void printFinalCards(Dealer dealer, Players players) {
        outputView.printNewLine();
        outputView.printFinalCards(dealer);
        for (Player player : players.getGamePlayers()) {
            outputView.printFinalCards(player);
        }
    }

    private ProfitResult calculateProfits(Dealer dealer, Players players) {
        BlackjackRule rule = new BlackjackRule();
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        for (Player player : players.getGamePlayers()) {
            Outcome outcome = rule.judge(player, dealer);
            playerProfits.put(player, player.calculateProfit(outcome));
        }
        return new ProfitResult(playerProfits);
    }

    private void printProfitResult(Dealer dealer, Players players) {
        ProfitResult profitResult = calculateProfits(dealer, players);
        outputView.printProfitResult(dealer.getName(), profitResult);
    }
}
