package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackController {

    public static final String SAME_NAME_ERROR = "중복된 이름을 사용할 수 없습니다.";

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final List<Player> players = playerSetUp();

        distributeInitialCards(players, dealer, cardDeck);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showGameResult(players, dealer);
    }

    private List<Player> playerSetUp() {
        final List<String> names = InputView.requestName();
        try {
            validateSameName(names);
            return generatePlayers(names);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return playerSetUp();
        }
    }

    private void validateSameName(final List<String> names) {
        final Set<String> changedNames = new HashSet<>(names);
        if (names.size() != changedNames.size()) {
            throw new IllegalArgumentException(SAME_NAME_ERROR);
        }
    }

    private List<Player> generatePlayers(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void distributeInitialCards(final List<Player> players, final Dealer dealer, final CardDeck cardDeck) {
        for (final Player player : players) {
            player.receiveInitialCards(cardDeck);
        }
        dealer.receiveInitialCards(cardDeck);
        OutputView.showDistributedCard(players, dealer);
    }

    private void playerGameProgress(final List<Player> players, final CardDeck cardDeck) {
        for (final Player player : players) {
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

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        OutputView.showFinalCardResult(players, dealer);
        final GameResult gameResult = new GameResult(dealer, players);
        OutputView.showGameResult(dealer, gameResult.getResultCounts());
        OutputView.showPlayersGameResult(players, gameResult.getPlayersResult());
    }
}