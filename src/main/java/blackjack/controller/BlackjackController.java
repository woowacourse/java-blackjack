package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    private static final String END_GAME_MARK = "n";

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final List<Player> players = playerSetUp();

        distributeCard(players, dealer, cardDeck);
        showPlayerName(players);
        showDistributedCard(players, dealer);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showFinalCardResult(players, dealer);
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

    private void showPlayerName(final List<Player> players) {
        final String status = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        OutputView.distributeMessage(status);
    }

    private void showDistributedCard(final List<Player> players, final Dealer dealer) {
        OutputView.showDealerCard(dealer.getName(), dealer.getMyCards().get(0));
        for (final Player player : players) {
            OutputView.showPlayerCard(player.getName(), player.getMyCards());
        }
    }

    private void playerGameProgress(final List<Player> players, final CardDeck cardDeck) {
        for (final Player player : players) {
            singlePlayerGameProgress(cardDeck, player);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Player player) {
        if (END_GAME_MARK.equals(askPlayerMoreCard(player))) {
            OutputView.showPlayerCard(player.getName(), player.getMyCards());
            return;
        }
        player.receiveCard(cardDeck.distribute());
        OutputView.showPlayerCard(player.getName(), player.getMyCards());
        if (isBust(player)) {
            return;
        }
        singlePlayerGameProgress(cardDeck, player);
    }

    private String askPlayerMoreCard(final Player player) {
        try {
            final String userInput = InputView.askMoreCard(player.getName());
            return validateMoreCardOption(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return askPlayerMoreCard(player);
        }
    }

    private String validateMoreCardOption(final String userInput) {
        if ("y".equals(userInput) || "n".equals(userInput)) {
            return userInput;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해야 합니다.");
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

    private void showFinalCardResult(final List<Player> players, final Dealer dealer) {
        OutputView.displayNewLine();
        OutputView.showCardResult(dealer.getName(), dealer.getMyCards(), dealer.calculate());
        for (final Player player : players) {
            OutputView.showCardResult(player.getName(), player.getMyCards(), player.calculate());
        }
    }

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        for (final Player player : players) {
            decideWinner(dealer, player);
        }
        OutputView.showGameResult(dealer.getName(), dealer.getWinCount(), players.size() - dealer.getWinCount());
        for (final Player player : players) {
            OutputView.showPlayerGameResult(player.getName(), player.getWin());
        }
    }

    private void decideWinner(final Dealer dealer, final Player player) {
        if (dealer.isWinner(player.calculate()) || player.isBust()) {
            player.lose();
            dealer.winSinglePlayer();
        }
    }
}
