package controller;

import domain.BlackJackGame;
import domain.Cards;
import domain.Dealer;
import domain.GameResult;
import domain.Player;
import domain.Players;
import view.InputView;
import view.ResultView;

public class BlackJackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final String participant = inputView.getPlayerNames();
        final BlackJackGame game = BlackJackGame.startGame(participant);
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final Cards cards = game.cards();

        resultView.printGameStartMessage(players, dealer);
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(game);
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        players.forEachPlayer(player -> drawPlayerTurn(player, cards));
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (!player.isDrawFinished()) {
            drawEachPlayers(player, cards);
        }
    }

    private void drawEachPlayers(Player player, Cards cards) {
        final boolean hit = inputView.askHitOrStand(player);
        if (hit) {
            hit(player, cards);
            return;
        }
        player.stand();
    }

    private void hit(Player player, Cards cards) {
        player.hit(cards);
        resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCardList()));
        printPlayerBustIfNeeded(player);
    }

    private void printPlayerBustIfNeeded(Player player) {
        if (player.getHandState().isBust()) {
            resultView.printPlayerBust(player.getName());
        }
    }

    private void drawDealerTurn(Dealer dealer, Cards cards) {
        resultView.printLineBreak();
        while (dealer.getScore().isDealerDraw()) {
            dealer.drawCard(cards);
            resultView.printDealerDrawMessage();
        }
        resultView.printLineBreak();
    }

    private void printResult(BlackJackGame game) {
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        printGameResult(players, dealer);
        printWinnerResult(game);
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(players, dealer);
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }

    private void printWinnerResult(BlackJackGame game) {
        final GameResult gameResult = game.calculateResult();
        resultView.printWinner(game.players(), gameResult);
    }
}
