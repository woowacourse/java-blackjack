package blackjack.controller;

import blackjack.domain.CardFactory;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Controller {

    public void play() {
        Dealer dealer = createDealer();
        Players players = createPlayers();

        initializeHands(dealer, players.getPlayers());
        playerTurn(dealer, players);
        dealerTurn(dealer);
        printScoreAndResult(dealer, players);
    }

    private Dealer createDealer() {
        CardFactory cardFactory = new CardFactory();
        Deck deck = new Deck(cardFactory.createBlackJackCard());
        return new Dealer(deck);
    }

    private Players createPlayers() {
        List<String> playerNames = InputView.readName();
        return Players.from(playerNames);
    }

    private void initializeHands(Dealer dealer, List<Player> players) {
        dealer.shuffleDeck();
        dealer.initializeHand(dealer.draw(), dealer.draw());
        for (Player player : players) {
            player.initializeHand(dealer.draw(), dealer.draw());
        }

        OutputView.printInitialHands(dealer, players);
    }

    private void playerTurn(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            hitOrStand(dealer, player);
        }
    }

    private void dealerTurn(Dealer dealer) {
        while (dealer.canHit()) {
            dealer.putCard(dealer.draw());
            OutputView.printDealerDraw(dealer);
        }
    }

    private void printScoreAndResult(Dealer dealer, Players players) {
        OutputView.printHandsWithScore(dealer, players.getPlayers());
        OutputView.printResult(dealer.getPlayersResult(players), dealer);
    }

    private void hitOrStand(Dealer dealer, Player player) {
        while (player.canHit() && InputView.readHitOrStand(player)) {
            player.putCard(dealer.draw());
            OutputView.printTotalHand(player);
        }

        if (!player.canHit()) {
            OutputView.printBust();
        }
    }
}
