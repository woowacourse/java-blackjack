package blackjack.controller;

import blackjack.domain.game.Answer;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.game.Winner;
import blackjack.dto.HumanDto;
import blackjack.view.Enter;
import blackjack.view.Enterable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {

    private static final Enterable enterable = new Enter();

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = createPlayers();

        drawCards(cardDeck, dealer, players);
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

    private void drawCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        drawInitCards(cardDeck, dealer, players);

        drawCardToPlayers(players, cardDeck);
        drawCardToDealer(dealer, cardDeck);
    }

    private void drawInitCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        OutputView.printDrawInitCards(dealer.getName(), players.getNames());
        dealer.drawInitCards(cardDeck.pickInit());
        for (Player player : players.getPlayers()) {
            player.drawInitCards(cardDeck.pickInit());
        }
        openInitCards(dealer, players);
    }

    private void openInitCards(final Dealer dealer, final Players players) {
        OutputView.printCards(dealer.getName(), HumanDto.getPartOfCards(dealer));
        for (Player player : players.getPlayers()) {
            OutputView.printCards(player.getName(), HumanDto.getCards(player));
        }
        OutputView.printNewLine();
    }

    private void drawCardToPlayers(final Players players, final CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawCardToPlayer(player, cardDeck);
        }
        OutputView.printNewLine();
    }

    private void drawCardToPlayer(final Player player, final CardDeck cardDeck) {
        while (player.isDrawable() && isDrawing(player)) {
            player.drawCard(cardDeck.pick());
            OutputView.printCards(player.getName(), HumanDto.getCards(player));
        }
    }

    private boolean isDrawing(final Player player) {
        try {
            OutputView.printDrawInstruction(player.getName());
            String input = InputView.inputDrawingAnswer(enterable);
            return Answer.isDraw(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isDrawing(player);
        }
    }

    private void drawCardToDealer(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.isDrawable()) {
            dealer.drawCard(cardDeck.pick());
            OutputView.printDrawDealer(dealer.getName(), Dealer.RECEIVED_MAXIMUM);
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
        showResult(dealer, winner, players);
    }

    private void openScore(final Dealer dealer, final Players players) {
        OutputView.printScore(dealer.getName(), HumanDto.getCards(dealer), dealer.sumOfCards());
        for (Player player : players.getPlayers()) {
            OutputView.printScore(player.getName(), HumanDto.getCards(player), player.sumOfCards());
        }
        OutputView.printNewLine();
    }

    private void showResult(final Dealer dealer, final Winner winner, final Players players) {
        OutputView.printDealerResult(dealer.getName(), winner.numberOfLosers(), winner.numberOfWinners());

        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player.getName(), winner.contains(player));
        }
    }
}
