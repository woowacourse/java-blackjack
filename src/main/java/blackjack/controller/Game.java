package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Game {

    public void run() {
        Deal deal = new Deal();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        dealInitCards(deal, dealer, players);
        openInitCards(dealer, players);

        drawPlayers(players, deal);
        drawDealer(dealer, deal);

        openResult(dealer, players);
        win(dealer, players);
    }

    private Players createPlayers() {
        OutputView.printPlayerNameInstruction();
        try {
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void dealInitCards(Deal deal, Dealer dealer, Players players) {
        OutputView.printDealCardMessage(Dealer.getName(), players.getNames());
        dealer.dealInit(deal.dealInit());
        for (Player player : players.getPlayers()) {
            player.dealInit(deal.dealInit());
        }
    }

    private void openInitCards(Dealer dealer, Players players) {
        OutputView.printCard(Dealer.getName(), dealer.getInitCard());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void drawPlayers(final Players players, final Deal deal) {
        for (Player player : players.getPlayers()) {
            drawPlayer(player, deal);
        }
    }

    private void drawPlayer(final Player player, final Deal deal) {
        while (isDrawing(player)) {
            player.hit(deal.draw());
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private boolean isDrawing(Player player) {
        return player.canDraw() && hitOrStay(player);
    }

    private boolean hitOrStay(final Player player) {
        try {
            OutputView.printTakeCardInstruction(player.getName());
            String input = InputView.inputTakeCardAnswer();
            return player.answer(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return hitOrStay(player);
        }
    }

    private void drawDealer(final Dealer dealer, final Deal deal) {
        while (dealer.canDraw()) {
            dealer.hit(deal.draw());
            OutputView.printDrawDealerCardMessage(Dealer.getName(), Dealer.RECEIVED_MAXIMUM);
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
            winner.decide(dealer, player);
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