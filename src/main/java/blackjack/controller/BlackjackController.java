package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        OutputView.printPlayerNameInstruction();

        CardMachine cardMachine = new CardMachine();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        noticeInitCard(cardMachine, dealer, players);
        openInitCard(dealer, players);

        distributeCardToPlayers(players, cardMachine);
        distributeCardToDealer(dealer, cardMachine);

        openResult(dealer, players);
        win(dealer, players);
    }

    private Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void noticeInitCard(CardMachine cardMachine, Dealer dealer, Players players) {
        OutputView.printReceiveInitCardMessage(Dealer.getName(), players.getNames());
        dealer.receiveInitCard(cardMachine.giveInitCard());
        for (Player player : players.getPlayers()) {
            player.receiveInitCard(cardMachine.giveInitCard());
        }
    }

    private void openInitCard(Dealer dealer, Players players) {
        OutputView.printCard(Dealer.getName(), dealer.getInitCard());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void distributeCardToPlayers(final Players players, final CardMachine cardMachine) {
        for (Player player : players.getPlayers()) {
            distributeCardToPlayer(player, cardMachine);
        }
    }

    private void distributeCardToPlayer(final Player player, final CardMachine cardMachine) {
        while (player.isReceived() && isReceived(player)) {
            player.receiveCard(cardMachine.giveCard());
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private boolean isReceived(final Player player) {
        try {
            OutputView.printTakeCardInstruction(player.getName());
            String input = InputView.inputTakeCardAnswer();
            return player.answer(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isReceived(player);
        }
    }

    private void distributeCardToDealer(final Dealer dealer, final CardMachine cardMachine) {
        while (dealer.isReceived()) {
            dealer.receiveCard(cardMachine.giveCard());
            OutputView.printTakeDealerCardsMessage(Dealer.getName(), Dealer.RECEIVED_MAXIMUM);
        }
        OutputView.printNewLine();
    }

    private void openResult(final Dealer dealer, final Players players) {
        openDealerResult(dealer);
        openPlayersResult(players);
    }

    private void openDealerResult(final Dealer dealer) {
        OutputView.printResult(Dealer.getName(), dealer.getCards(), dealer.getTotal());
        OutputView.printNewLine();
    }

    private void openPlayersResult(final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printResult(player.getName(), player.getCards(), player.getTotal());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void win(final Dealer dealer, final Players players) {
        Winner winner = new Winner();
        for (Player player : players.getPlayers()) {
            winner.compare(dealer, player);
        }
        OutputView.printWinnerTitle();
        winDealer(winner, players);
        winPlayers(winner, players);
    }

    private void winDealer(final Winner winner, final Players players) {
        int winPlayersCount = winner.winPlayersCount();
        int losePlayersCount = players.getPlayerCount() - winner.winPlayersCount();
        OutputView.printDealerScore(Dealer.getName(), losePlayersCount, winPlayersCount);
    }

    private void winPlayers(final Winner winner, final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerScore(player.getName(), winner.contains(player));
        }
    }
}