package controller;

import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
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
        for (Player player : players.getPlayers()) {
            outputView.printInitBetMoney(player);
            player.initBet(inputView.readBetMoney());
        }
    }

    private void hitTwice(final Dealer dealer, final Players players, final Deck deck) {
        outputView.printEmptyLine();
        outputView.printHitTwice(players);

        hitTwiceEachParticipant(dealer, players, deck);

        outputView.printDealerFirstCard(dealer);
        outputView.printPlayersCard(players);

        outputView.printEmptyLine();
    }

    private void hitTwiceEachParticipant(final Dealer dealer, final Players players, final Deck deck) {
        dealer.hitTwice(deck);
        players.hitTwice(deck);
    }
}
