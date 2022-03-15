package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Game {

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        dealInitCards(cardDeck, dealer, players);
        openInitCards(dealer, players);

        drawPlayers(players, cardDeck);
        drawDealer(dealer, cardDeck);

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

    private void dealInitCards(CardDeck cardDeck, Dealer dealer, Players players) {
        OutputView.printDealCardMessage(dealer.getName(), players.getNames());
        dealer.dealInit(cardDeck.dealInit());
        for (Player player : players.getPlayers()) {
            player.dealInit(cardDeck.dealInit());
        }
    }

    private void openInitCards(Dealer dealer, Players players) {
        OutputView.printCard(dealer.getName(), dealer.getInitCard());
        OutputView.printNewLine();
        for (Player player : players.getPlayers()) {
            OutputView.printCard(player.getName(), player.getCards());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    private void drawPlayers(final Players players, final CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawPlayer(player, cardDeck);
        }
    }

    private void drawPlayer(final Player player, final CardDeck cardDeck) {
        while (isDrawing(player)) {
            player.hit(cardDeck.drawCard());
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

    private void drawDealer(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.hit(cardDeck.drawCard());
            OutputView.printDrawDealerCardMessage(dealer.getName(), Dealer.RECEIVED_MAXIMUM);
        }
        OutputView.printNewLine();
    }

    private void openResult(final Dealer dealer, final Players players) {
        openDealerResult(dealer);
        openPlayersResult(players);
    }

    private void openDealerResult(final Dealer dealer) {
        OutputView.printResult(dealer.getName(), dealer.getCards(), dealer.getTotal());
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
        winDealer(winner, dealer, players);
        winPlayers(winner, players);
    }

    private void winDealer(final Winner winner, Dealer dealer, final Players players) {
        OutputView.printDealerScore(dealer.getName(), winner.countWinner(), winner.countLoser(players));
    }

    private void winPlayers(final Winner winner, final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerScore(player.getName(), winner.contains(player));
        }
    }
}