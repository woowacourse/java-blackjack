package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void run() {
        Players players = createPlayers();
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();

        play(players, cardDeck, dealer);
    }

    private Players createPlayers() {
        try {
            OutputView.printPlayerNameInstruction();
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void play(Players players, CardDeck cardDeck, Dealer dealer) {
        dealInitCards(cardDeck, dealer, players);
        Betting betting = bet(players);
        openInitCards(dealer, players);

        drawPlayers(players, cardDeck);
        drawDealer(dealer, cardDeck);

        Result result = new Result();
        result.openResult(players, dealer);
        result.openProfits(betting, dealer, players);
    }

    private void dealInitCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        dealer.dealInit(cardDeck.dealInit());
        players.dealInit(cardDeck);
    }

    private Betting bet(Players players) {
        Betting betAmount = new Betting();
        for (Player player : players.getPlayers()) {
            betAmount = bets(betAmount, player);
        }
        return betAmount;
    }

    private Betting bets(Betting betAmount, Player player) {
        try {
            OutputView.printBettingInstruction(player.getName());
            betAmount.bet(player, BettingMoney.of(InputView.inputBettingMoney()));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return bets(betAmount, player);
        }
        return betAmount;
    }

    private void openInitCards(final Dealer dealer, final Players players) {
        OutputView.printDealCardMessage(dealer.getName(), players.getNames());
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

    private boolean isDrawing(final Player player) {
        return player.canDraw() && hits(player);
    }

    private boolean hits(final Player player) {
        try {
            OutputView.printTakeCardInstruction(player.getName());
            String input = InputView.inputTakeCardAnswer();
            return HitOption.hits(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return hits(player);
        }
    }

    private void drawDealer(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.hit(cardDeck.drawCard());
            OutputView.printDrawDealerCardMessage(dealer.getName(), Dealer.RECEIVED_MAXIMUM);
        }
        OutputView.printNewLine();
    }
}