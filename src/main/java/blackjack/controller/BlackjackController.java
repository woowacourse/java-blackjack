package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final Players players = playerSetUp();

        distributeInitialCards(players, dealer, cardDeck);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showGameResult(players, dealer);
    }

    private Players playerSetUp() {
        final List<String> names = InputView.requestName();
        final List<Integer> bettingMoneys = InputView.requestBettingMoney(names);
        try {
            return new Players(names, bettingMoneys);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return playerSetUp();
        }
    }

    private void distributeInitialCards(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        for (final Player player : players.getPlayers()) {
            player.receiveInitialCards(cardDeck.distribute(), cardDeck.distribute());
        }
        dealer.receiveInitialCards(cardDeck.distribute(), cardDeck.distribute());
        OutputView.showDistributedCard(players.getPlayers(), dealer);
    }

    private void playerGameProgress(final Players players, final CardDeck cardDeck) {
        for (final Player player : players.getPlayers()) {
            singlePlayerGameProgress(cardDeck, player);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Player player) {
        while (!player.isBust() && InputView.askPlayerMoreCard(player)) {
            player.receiveOneCard(cardDeck.distribute());
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
            OutputView.dealerMoreCard();
            dealer.receiveOneCard(cardDeck.distribute());
        }
    }

    private void showGameResult(final Players players, final Dealer dealer) {
        OutputView.showFinalCardResult(players.getPlayers(), dealer);
        final GameResult gameResult = new GameResult(dealer, players.getPlayers());
        OutputView.showGameResult(dealer, gameResult.getDealerProfit());
        OutputView.showPlayersProfit(gameResult.getPlayersProfit());
    }
}