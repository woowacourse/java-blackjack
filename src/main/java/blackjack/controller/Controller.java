package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void play() {
        Players players = createPlayers();
        Dealer dealer = createDealer();

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

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Betting betting = new Betting(InputView.readBettings(playerName));
            players.add(new Player(playerName, betting));
        }
        return new Players(players);
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
        OutputView.printResult(dealer.judgePlayersResult(players), dealer);
    }

    private void hitOrStand(Dealer dealer, Player player) {
        if (player.isBlackjack()) {
            OutputView.printPlayerBlackjack(player.getName());
            return;
        }
        while (player.canHit() && InputView.readHitOrStand(player)) {
            player.putCard(dealer.draw());
            OutputView.printTotalHand(player);
        }
        if (player.isBust()) {
            OutputView.printBust(player.getName());
        }
    }
}
