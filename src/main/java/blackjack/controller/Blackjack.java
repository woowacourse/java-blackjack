package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.Enter;
import blackjack.view.Enterable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

    private static final Enterable enterable = new Enter();

    public void run() {
        CardMachine cardMachine = new CardMachine();
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = createPlayers();

        drawCards(cardMachine, dealer, players);
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

    private void drawCards(final CardMachine cardMachine, final Dealer dealer, final Players players) {
        drawInitCards(cardMachine, dealer, players);

        drawCardToPlayers(players, cardMachine);
        drawCardToDealer(dealer, cardMachine);
    }

    private void drawInitCards(final CardMachine cardMachine, final Dealer dealer, final Players players) {
        OutputView.printDrawInitCards(Dealer.getName(), players.getNames());
        dealer.drawInitCards(cardMachine.pickInitCards());
        for (Player player : players.getPlayers()) {
            player.drawInitCards(cardMachine.pickInitCards());
        }
        openInitCards(dealer, players);
    }

    private void openInitCards(final Dealer dealer, final Players players) {
        OutputView.printCards(Dealer.getName(), getDealerInitCards(dealer));
        for (Player player : players.getPlayers()) {
            OutputView.printCards(player.getName(), getPlayerCards(player));
        }
        OutputView.printNewLine();
    }

    private List<String> getPlayerCards(final Player player) {
        return player.showCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    private List<String> getDealerInitCards(final Dealer dealer) {
        return dealer.showPartOfCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    private void drawCardToPlayers(final Players players, final CardMachine cardMachine) {
        for (Player player : players.getPlayers()) {
            drawCardToPlayer(player, cardMachine);
        }
        OutputView.printNewLine();
    }

    private void drawCardToPlayer(final Player player, final CardMachine cardMachine) {
        while (player.isDrawable() && isDrawing(player)) {
            player.drawCard(cardMachine.pickCard());
            OutputView.printCards(player.getName(), getPlayerCards(player));
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

    private void drawCardToDealer(final Dealer dealer, final CardMachine cardMachine) {
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
            winner.compete(dealer, player);
        }
        OutputView.printResultTitle();
        showResult(winner, players);
    }

    private void openScore(final Dealer dealer, final Players players) {
        OutputView.printScore(Dealer.getName(), getDealerCards(dealer), dealer.showSumOfCards());
        for (Player player : players.getPlayers()) {
            OutputView.printScore(player.getName(), getPlayerCards(player), player.showSumOfCards());
        }
        OutputView.printNewLine();
    }

    private List<String> getDealerCards(final Dealer dealer) {
        return dealer.showCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    private void showResult(final Winner winner, final Players players) {
        OutputView.printDealerResult(Dealer.getName(), winner.numberOfLosers(), winner.numberOfWinners());

        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player.getName(), winner.contains(player));
        }
    }
}
