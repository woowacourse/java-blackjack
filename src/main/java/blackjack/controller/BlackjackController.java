package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final Players players = playerSetUp();

        distributeInitialCard(players, dealer, cardDeck);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showFinalCardResult(players, dealer);
        showGameResult(players, dealer);
    }

    private Players playerSetUp() {
        try {
            final List<String> playerName = InputView.requestName();
            return Players.of(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return playerSetUp();
        }
    }

    private void distributeInitialCard(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        dealer.receiveInitialCard(cardDeck);
        players.receiveInitialCard(cardDeck);
        OutputView.showDistributionMessage(dealer, players);
        OutputView.showDealerCard(dealer);
        OutputView.showAllPlayerCard(players);
    }

    private void playerGameProgress(final Players players, final CardDeck cardDeck) {
        for (final Player player : players.getPlayers()) {
            singlePlayerGameProgress(cardDeck, player);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Player player) {
        while (!player.isBust() && InputView.requestMoreCard(player)) {
            player.receiveAdditionalCard(cardDeck.distribute());
            OutputView.showPlayerCard(player);
        }
        if (player.isBust()) {
            OutputView.showBustMessage();
            return;
        }
        OutputView.showPlayerCard(player);
    }

    private void dealerGameProgress(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.checkMoreCardAvailable()) {
            OutputView.showDealerGotMoreCard(dealer);
            dealer.receiveAdditionalCard(cardDeck.distribute());
        }
    }

    private void showFinalCardResult(final Players players, final Dealer dealer) {
        OutputView.showNewLine();
        OutputView.showCardResult(dealer);
        OutputView.showAllPlayerCardResult(players);
    }

    private void showGameResult(final Players players, final Dealer dealer) {
        OutputView.showDealerGameResult(dealer, players.checkDealerResult(dealer));
        OutputView.showPlayerGameResult(players.checkAllPlayerResult(dealer));
    }
}