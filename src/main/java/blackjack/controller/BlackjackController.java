package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            player.receiveInitialCard(cardDeck);
        }
        dealer.receiveInitialCard(cardDeck);
    }

    private void showPlayerName(final List<Player> players) {
        OutputView.distributeMessage(players);
    }

    private void showDistributedCard(final List<Player> players, final Dealer dealer) {
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
        if (END_GAME_MARK.equals(askPlayerMoreCard(player))) {
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
        OutputView.showCardResult(dealer);
        for (final Player player : players) {
            OutputView.showCardResult(player);
        }
    }

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        final Result result = new Result(players, dealer);
        final Map<String, Integer> dealerResult = result.checkDealerResult();
        System.out.println("dealerResult = " + dealerResult);
        final Map<Player, String> playerResult = result.checkPlayerResult();
        System.out.println("playerResult = " + playerResult);
        OutputView.showDealerGameResult(dealerResult);
        OutputView.showPlayerGameResult(playerResult);
    }
}
