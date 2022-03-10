package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.Enter;
import blackjack.view.Enterable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private static final Enterable enterable = new Enter();

    public void run() {
        OutputView.printPlayerNameInstruction();

        CardMachine cardMachine = new CardMachine();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        distributeCards(cardMachine, dealer, players);
        showWinner(dealer, players);
    }

    private Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerNames(enterable));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void distributeCards(final CardMachine cardMachine, final Dealer dealer, final Players players) {
        distributeInitCards(cardMachine, dealer, players);

        distributeCardToPlayers(players, cardMachine);
        distributeCardToDealer(dealer, cardMachine);
    }

    private void distributeInitCards(final CardMachine cardMachine, final Dealer dealer, final Players players) {
        OutputView.printNewLine();
        OutputView.printReceiveInitCards(Dealer.getName(), players.getNames());
        OutputView.printNewLine();
        dealer.receiveInitCards(cardMachine.pickInitCards());
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(cardMachine.pickInitCards());
        }
        openInitCard(dealer, players);
    }

    private void openInitCard(final Dealer dealer, final Players players) {
        OutputView.printCards(Dealer.getName(), dealer.showPartOfCards());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printCards(player.getName(), player.showCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void distributeCardToPlayers(final Players players, final CardMachine cardMachine) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(player, cardMachine);
        }
        OutputView.printNewLine();
    }

    private void distributeCardToPlayer(final Player player, final CardMachine cardMachine) {
        while (player.isReceived() && isReceivingMoreCard(player)) {
            player.receiveCard(cardMachine.pickCard());
            OutputView.printCards(player.getName(), player.showCards());
            OutputView.printNewLine();
        }
    }

    private boolean isReceivingMoreCard(final Player player) {
        try {
            OutputView.printReceiveMoreCard(player.getName());
            OutputView.printNewLine();
            String input = InputView.inputReceiveMoreCardAnswer(enterable);
            return player.answer(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isReceivingMoreCard(player);
        }
    }

    private void distributeCardToDealer(final Dealer dealer, final CardMachine cardMachine) {
        while (dealer.isReceived()) {
            dealer.receiveCard(cardMachine.pickCard());
            OutputView.printReceiveDealerMoreCard(Dealer.getName(), Dealer.RECEIVED_MAXIMUM);
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void showWinner(final Dealer dealer, final Players players) {
        openDealerScore(dealer);
        openPlayersScore(players);

        showResult(dealer, players);
    }

    private void openDealerScore(final Dealer dealer) {
        OutputView.printScore(Dealer.getName(), dealer.showCards(), dealer.showSumOfCards());
        OutputView.printNewLine();
    }

    private void openPlayersScore(final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printScore(player.getName(), player.showCards(), player.showSumOfCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void showResult(final Dealer dealer, final Players players) {
        Winner winner = new Winner();
        for (Player player : players.getPlayers()) {
            winner.compare(dealer, player);
        }
        OutputView.printResultTitle();
        showDealerResult(winner, players);
        showPlayersResult(winner, players);
    }

    private void showDealerResult(final Winner winner, final Players players) {
        int numberOfWinners = winner.numberOfWinners();
        int numberOfLosers = players.numberOfPlayers() - winner.numberOfWinners();

        OutputView.printDealerResult(Dealer.getName(), numberOfLosers, numberOfWinners);
        OutputView.printNewLine();
    }

    private void showPlayersResult(final Winner winner, final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player.getName(), winner.contains(player));
            OutputView.printNewLine();
        }
    }
}