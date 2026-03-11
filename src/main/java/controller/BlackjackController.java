package controller;

import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int BUST_THRESHOLD = 21;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputView.inputPlayers();
        Players players = new Players(names);
        Dealer dealer = new Dealer("딜러");
        Deck deck = new Deck();
        dealInitialCards(dealer, players, deck);
        dealInitialCards(dealer, players, deck);
        printInitialState(dealer, players, names);
        playAllPlayerTurns(players, deck);
        playDealerTurn(players.getDealer(), deck);
        printFinalState(players);
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
        boolean cardShown = false;
        while (player.calculateScore() <= BUST_THRESHOLD && inputView.askHit(player.getName())) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player);
            cardShown = true;
        }
        if (!cardShown) {
            outputView.printPlayerCards(player);
        }
    }

    private void playDealerTurn(Player dealer, Deck deck) {
        while (dealer.calculateScore() <= DEALER_HIT_THRESHOLD) {
            dealer.addCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printFinalState(Players players) {
        System.out.println();
        outputView.printFinalCards(players.getDealer());
        for (Player player : players.getGamePlayers()) {
            outputView.printFinalCards(player);
        }
        Map<Player, Result> results = players.judge();
        outputView.printFinalResult(players.getDealer(), results);
    }
}
