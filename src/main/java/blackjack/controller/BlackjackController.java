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
        OutputView.printDistributeInitCards(Dealer.getName(), players.getNames());
        dealer.drawInitCards(cardMachine.pickInitCards());
        for (Player player : players.getPlayers()) {
            player.drawInitCards(cardMachine.pickInitCards());
        }
        OutputView.printInitCard(dealer, players);
    }

    private void distributeCardToPlayers(final Players players, final CardMachine cardMachine) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(player, cardMachine);
        }
        OutputView.printNewLine();
    }

    private void distributeCardToPlayer(final Player player, final CardMachine cardMachine) {
        while (player.isDrawable() && isDrawing(player)) {
            player.drawCard(cardMachine.pickCard());
            OutputView.printCards(player.getName(), player.showCards());
        }
    }

    private boolean isDrawing(final Player player) {
        try {
            OutputView.printDrawInstruction(player.getName());
            String input = InputView.inputDrawingAnswer(enterable);
            return player.answer(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isDrawing(player);
        }
    }

    private void distributeCardToDealer(final Dealer dealer, final CardMachine cardMachine) {
        while (dealer.isDrawable()) {
            dealer.drawCard(cardMachine.pickCard());
            OutputView.printDrawDealer(Dealer.getName(), Dealer.RECEIVED_MAXIMUM);
        }
        OutputView.printNewLine();
    }

    private void showWinner(final Dealer dealer, final Players players) {
        openScore(dealer, players);

        Winner winner = new Winner();
        for (Player player : players.getPlayers()) {
            winner.compare(dealer, player);
        }
        OutputView.printResultTitle();
        showResult(winner, players);
    }

    private void openScore(final Dealer dealer, final Players players) {
        OutputView.printScore(Dealer.getName(), dealer.showCards(), dealer.showSumOfCards());
        for (Player player : players.getPlayers()) {
            OutputView.printScore(player.getName(), player.showCards(), player.showSumOfCards());
        }
        OutputView.printNewLine();
    }

    private void showResult(final Winner winner, final Players players) {
        int numberOfWinners = winner.numberOfWinners();
        int numberOfLosers = players.numberOfPlayers() - winner.numberOfWinners();
        OutputView.printDealerResult(Dealer.getName(), numberOfLosers, numberOfWinners);

        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player.getName(), winner.contains(player));
        }
    }
}
