package controller;

import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputNames();
        Players players = makePlayers(names);
        Dealer dealer = new Dealer();
        Deck deck = makeDeck();
        initBetMoney(players);

        hitTwice(dealer, players, deck);

        new ProgressController(inputView, outputView).run(dealer, players, deck);
        new ResultController(outputView).run(dealer, players);
    }

    private List<String> inputNames() {
        outputView.printInputNames();
        return inputView.readNames();
    }

    private Players makePlayers(final List<String> names) {
        return new Players(names);
    }

    private Deck makeDeck() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        return deck;
    }

    private void initBetMoney(final Players players) {
        for(String name : players.getPlayerNames()) {
            outputView.printInitBetMoney(name);
            players.initBet(inputView.readBetMoney(), name);
        }
    }

    private void hitTwice(final Dealer dealer, final Players players, final Deck deck) {
        outputView.printHitTwice(players);
        hitTwiceEachParticipant(dealer, players, deck);
        outputView.printDealerFirstCard(dealer);
        outputView.printPlayersCard(players);
    }

    private void hitTwiceEachParticipant(final Dealer dealer, final Players players, final Deck deck) {
        dealer.hitTwice(deck);
        players.hitTwice(deck);
    }
}
