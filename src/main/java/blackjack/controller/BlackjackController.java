package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = CardDeckFactory.make();
        final Dealer dealer = new Dealer();
        final Players players = playerSetUp();

        distributeInitialCard(players, dealer, cardDeck);
        progressEveryPlayerGame(players, cardDeck);
        progressDealerGame(dealer, cardDeck);
        showFinalResult(players, dealer);
    }

    private Players playerSetUp() {
        try {
            final List<String> playerName = InputView.requestName();
            final List<Integer> playerMoney = InputView.requestBettingMoney(playerName);
            return Players.of(playerName, playerMoney);
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
        OutputView.showEveryPlayerCard(players);
    }

    private void progressEveryPlayerGame(final Players players, final CardDeck cardDeck) {
        for (final Player player : players.getPlayers()) {
            progressSinglePlayerGame(cardDeck, player);
        }
    }

    private void progressSinglePlayerGame(final CardDeck cardDeck, final Player player) {
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

    private void progressDealerGame(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.canGetMoreCard()) {
            OutputView.showDealerGotMoreCard(dealer);
            dealer.receiveAdditionalCard(cardDeck.distribute());
        }
    }

    private void showFinalResult(final Players players, final Dealer dealer) {
        OutputView.showNewLine();
        OutputView.showCardResult(dealer);
        OutputView.showEveryPlayerCardResult(players);

        final Map<Player, Double> playerProfit = players.generateEveryPlayerProfit(dealer);
        dealer.calculateProfit(playerProfit);
        OutputView.showDealerProfit(dealer);
        OutputView.showPlayerProfit(playerProfit);
    }
}
