package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.BlackjackGame;
import blackjack.model.bet.BetAmounts;
import blackjack.model.gameresult.ProfitResult;
import blackjack.model.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;


public class BlackjackController {

    private final BlackjackGame blackjackGame;

    public BlackjackController(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        Users users = retryUntilSuccess(() -> blackjackGame.createUsers(InputView::readPlayerName));

        BetAmounts betAmounts = retryUntilSuccess(
                () -> blackjackGame.createBetAmount(InputView::readBetAmount, users));

        drawInitCardsAndPrint(users);

        hit(users);

        printHandStatus(users);

        printProfitResult(users, betAmounts);

        blackjackGame.end(InputView::closeScanner);
    }

    private void drawInitCardsAndPrint(Users users) {
        blackjackGame.drawInitCards(users);
        OutputView.printInitCards(users);
    }

    private void hit(Users users) {
        blackjackGame.hitPlayers(users.getPlayers(), InputView::readHitCommand, OutputView::printPlayerCards,
                OutputView::printCantHit);
        blackjackGame.hitDealer(users.getDealer(), OutputView::printDealerHit);
    }

    private void printHandStatus(Users users) {
        OutputView.printHandStatus(users);
    }

    private void printProfitResult(Users users, BetAmounts betAmounts) {
        ProfitResult profitResult = blackjackGame.judgeWinner(users, betAmounts);
        OutputView.printGameResult(profitResult, users);
    }
}
