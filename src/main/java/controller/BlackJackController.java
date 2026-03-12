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
        final String participant = inputView.getParticipant();
        final BlackJackGame game = BlackJackGame.startGame(participant);
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final Cards cards = game.cards();

        resultView.printGameStartSection(players, dealer);
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(players, dealer);
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        for (int index = 0; index < players.getSize(); index++) {
            drawPlayerTurn(players.getPlayer(index), cards);
        }
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (inputView.getMoreCards(player)) {
            player.drawCard(cards);
            resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCardList()));
            if (player.checkBust()) {
                resultView.printPlayerBust(player.getName());
                return;
            }
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

    private void printResult(Players players, Dealer dealer) {
        printGameResult(players, dealer);
        printWinnerResult(players, dealer);
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(players, dealer);
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }

    private void printWinnerResult(Players players, Dealer dealer) {
        final GameResult gameResult = players.calculateResult(dealer);
        resultView.printWinner(players, gameResult);
    }
}
