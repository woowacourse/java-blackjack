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

        provideInitCardsAndPrint(users);

        blackjackGame.hit(users, InputView::readHitCommand, OutputView::printPlayerCards, OutputView::printDealerHit);

        printHandStatus(users);

        printProfitResult(users, betAmounts);

        InputView.closeScanner();
    }

    private void provideInitCardsAndPrint(Users users) {
        blackjackGame.provideInitCards(users);
        OutputView.printInitCards(users);
    }

    private void printHandStatus(Users users) {
        OutputView.printHandStatus(users);
    }

    private void printProfitResult(Users users, BetAmounts betAmounts) {
        ProfitResult profitResult = blackjackGame.determineWinner(users, betAmounts);
        OutputView.printGameResult(profitResult, users);
    }
}
