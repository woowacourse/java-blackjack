package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final List<Player> players = playerSetUp();

        distributeCard(players, dealer, cardDeck);
        showDistributedCard(players, dealer);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showGameResult(players, dealer);
    }

    private List<Player> playerSetUp() {
        final List<String> names = InputView.requestName();
        final List<Player> players = new ArrayList<>();
        for (final String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    private void distributeCard(final List<Player> players, final Dealer dealer, final CardDeck cardDeck) {
        for (final Player player : players) {
            player.receiveCard(cardDeck.distribute());
            player.receiveCard(cardDeck.distribute());
        }
        dealer.receiveCard(cardDeck.distribute());
        dealer.receiveCard(cardDeck.distribute());
    }

    private void showDistributedCard(final List<Player> players, final Dealer dealer) {
        OutputView.distributeMessage(players);
        OutputView.showDealerCard(dealer);
        OutputView.showPlayersCard(players);
    }

    private void playerGameProgress(final List<Player> players, final CardDeck cardDeck) {
        for (final Player player : players) {
            singlePlayerGameProgress(cardDeck, player);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Player player) {
        if (!InputView.askPlayerMoreCard(player)) {
            OutputView.showPlayerCard(player);
            return;
        }
        player.receiveCard(cardDeck.distribute());
        OutputView.showPlayerCard(player);
        if (isBust(player)) {
            return;
        }
        singlePlayerGameProgress(cardDeck, player);
    }

    private boolean isBust(final Player player) {
        if (player.isBust()) {
            OutputView.bustMessage();
            return true;
        }
        return false;
    }

    private void dealerGameProgress(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.checkMoreCardAvailable()) {
            OutputView.dealerMoreCard();
            dealer.receiveCard(cardDeck.distribute());
        }
    }

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        showFinalCardResult(players, dealer);
        for (final Player player : players) {
            decideWinner(dealer, player);
        }
        OutputView.showGameResult(dealer.getName(), dealer.getWinCount(), players.size() - dealer.getWinCount());
        OutputView.showPlayersGameResult(players);
    }

    private void showFinalCardResult(final List<Player> players, final Dealer dealer) {
        OutputView.displayNewLine();
        OutputView.showCardResult(dealer);
        OutputView.showCardsResult(players);
    }

    private void decideWinner(final Dealer dealer, final Player player) {
        if (dealer.isWinner(player.calculate()) || player.isBust()) {
            player.lose();
            dealer.increaseWinCount();
        }
    }
}