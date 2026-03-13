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
        Players players = new Players(names);
        Dealer dealer = new Dealer("딜러");
        Deck deck = new Deck();
        dealInitialCards(dealer, players, deck);
        dealInitialCards(dealer, players, deck);
        printInitialState(dealer, players, names);
        playAllPlayerTurns(players, deck);
        playDealerTurn(dealer, deck);
        printFinalState(dealer, players);
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
        Referee referee = new Referee();
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players.getGamePlayers()) {
            results.put(player, referee.judge(player.getScore(), dealer.getScore()));
        }
        outputView.printFinalResult(dealer, results);
    }
}
