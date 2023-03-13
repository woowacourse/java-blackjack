package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Profit;
import blackjack.view.DrawIntention;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    public static final int CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Deck deck = new Deck();
            Dealer dealer = new Dealer(deck.getCards(CARD_COUNT));
            Players players = generatePlayers(deck);
            outputView.showInitialCards(dealer, players);
            playGame(deck, dealer, players);
            closeGame(dealer, players);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private Players generatePlayers(final Deck deck) {
        outputView.printRequestPlayerNames();
        List<String> playerNames = inputView.readPlayerNames();
        List<String> bettingMoneys = new ArrayList<>();
        for (String playerName : playerNames) {
            outputView.printRequestBettingMoney(playerName);
            bettingMoneys.add(inputView.readBettingMoney());
        }
        return Players.of(playerNames, bettingMoneys, deck);
    }

    private void playGame(final Deck deck, final Dealer dealer, final Players players) {
        playAllPlayer(deck, players);
        playDealer(deck, dealer);
    }

    private void playAllPlayer(final Deck deck, final Players players) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(deck, player);
        }
    }

    private void playEachPlayer(Deck deck, Player player) {
        while (player.isAbleToReceive() && wantHit(player.getName())) {
            player.hit(deck.getCard());
            outputView.printPlayerCards(player);
        }
        if (player.isAbleToReceive()) {
            player.stay();
        }
    }

    private boolean wantHit(final String playerName) {
        outputView.printRequestIntention(playerName);
        return DrawIntention.of(inputView.readPlayerIntention().toLowerCase()).equals(DrawIntention.YES);
    }

    private void playDealer(final Deck deck, final Dealer dealer) {
        while (dealer.isAbleToReceive()) {
            outputView.printDealerReceived();
            dealer.hit(deck.getCard());
        }
    }

    private void closeGame(final Dealer dealer, final Players players) {
        outputView.printFinalCards(dealer, players);
        Profit profit = Profit.of(dealer, players);
        outputView.printDealerResults(profit.calculateDealerScore());
        players.getPlayers().forEach(
                player -> outputView.printPlayerResult(player, profit.getPlayerProfit(player.getName())));
    }
}
