package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void play(Dealer dealer, Players players) {
        CardDeck cardDeck = new CardDeck();

        dealInitCards(cardDeck, dealer, players);
        openInitCards(dealer, players);

        drawPlayers(players, cardDeck);
        drawDealer(dealer, cardDeck);
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
            return HitOption.hits(input);
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
}