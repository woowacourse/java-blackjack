package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final List<Player> players = playerSetUp();

        distributeInitialCard(players, dealer, cardDeck);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showFinalCardResult(players, dealer);
        showGameResult(players, dealer);
    }

    private List<Player> playerSetUp() {
        final List<String> names = InputView.requestName();
        final List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return players;
    }

    private void distributeInitialCard(final List<Player> players, final Dealer dealer, final CardDeck cardDeck) {
        dealer.receiveInitialCard(cardDeck);
        for (final Player player : players) {
            player.receiveInitialCard(cardDeck);
        }
        OutputView.showDistributionMessage(dealer, players);
        OutputView.showDealerCard(dealer);
        for (final Player player : players) {
            OutputView.showPlayerCard(player);
        }
    }

    private void playerGameProgress(final List<Player> players, final CardDeck cardDeck) {
        for (final Player player : players) {
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

    private void showFinalCardResult(final List<Player> players, final Dealer dealer) {
        OutputView.showNewLine();
        OutputView.showCardResult(dealer);
        for (final Player player : players) {
            OutputView.showCardResult(player);
        }
    }

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        final Result result = new Result(players, dealer);
        final Map<String, Integer> dealerResult = result.checkDealerResult();
        final Map<Player, String> playerResult = result.checkPlayerResult();
        OutputView.showDealerGameResult(dealer, dealerResult);
        OutputView.showPlayerGameResult(playerResult);
    }
}