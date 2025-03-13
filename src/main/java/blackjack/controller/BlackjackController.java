package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(BlackjackProcessManager blackjackProcessManager) {
        this.blackjackProcessManager = blackjackProcessManager;
    }

    public void run() {
        List<String> names = InputView.readNames();
        Deck deck = blackjackProcessManager.generateDeck();
        Players players = blackjackProcessManager.generatePlayers(names);
        Dealer dealer = blackjackProcessManager.generateDealer();

        betting(players);
        executeGameFlow(deck, players, dealer);

        printResult(players, dealer);
    }

    private void betting(Players players) {
        for (Player player : players.getPlayers()) {
            int money = InputView.readBettingMoney(player.getName());
            player.betting(money);
        }
    }

    private void executeGameFlow(Deck deck, Players players, Dealer dealer) {
        giveStartingCards(deck, players, dealer);
        players.checkBlackjack();

        giveMoreCardFor(deck, players);
        giveMoreCardFor(deck, dealer);
    }

    private void giveStartingCards(Deck deck, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCardsFor(deck, player);
        }

        blackjackProcessManager.giveStartingCardsFor(deck, dealer);

        OutputView.printStartingCardsStatuses(dealer, players);
    }

    private void giveMoreCardFor(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            giveMoreCardFor(deck, player);
        }
    }

    private void giveMoreCardFor(Deck deck, Dealer dealer) {
        while (dealer.canTakeCard()) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(deck, dealer);
        }
    }

    private void giveMoreCardFor(Deck deck, Player player) {
        Confirmation confirmation = InputView.askToGetMoreCard(player);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(player);
            return;
        }

        blackjackProcessManager.giveCard(deck, player);
        OutputView.printCardResult(player);

        if (player.isBusted()) {
            OutputView.printBustedPlayer(player);
            return;
        }

        if (player.canTakeCard()) {
            giveMoreCardFor(deck, player);
        }
    }

    private void printResult(Players players, Dealer dealer) {
        players.adjustBalance(dealer);

        int playersTotalRevenue = players.getTotalRevenue();
        Map<Player, Integer> revenueMap = players.getRevenueMap();

        OutputView.printRevenue(playersTotalRevenue, revenueMap);
    }
}
