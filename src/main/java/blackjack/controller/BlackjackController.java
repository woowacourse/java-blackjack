package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.BlackjackGame;
import blackjack.model.BetAmount;
import blackjack.model.card.HitCommand;
import blackjack.model.gameresult.PlayersGameResult;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;


public class BlackjackController {

    private final BlackjackGame blackjackGame;

    public BlackjackController(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        Users users = retryUntilSuccess(() -> blackjackGame.createUsers(InputView::readPlayerName));

        List<BetAmount> betAmounts = retryUntilSuccess(
                () -> blackjackGame.createBetAmount(InputView::readBetAmount, users));

        provideInitCardsAndPrint(users);

        blackjackGame.hit(users, InputView::readHitCommand);

        printHandStatus(users);

        printGameResult(users);

        InputView.closeScanner();
    }

    private void provideInitCardsAndPrint(Users users) {
        blackjackGame.provideInitCards(users);
        OutputView.printInitCards(users);
    }

    private void printHandStatus(Users users) {
        OutputView.printHandStatus(users);
    }

    private void printGameResult(Users users) {
        PlayersGameResult playersGameResult = users.determineWinner();
        OutputView.printGameResult(playersGameResult, users);
    }
}
