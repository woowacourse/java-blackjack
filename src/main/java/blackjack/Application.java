package blackjack;

import blackjack.controller.BlackjackGame;
import blackjack.controller.Result;
import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Result result = new Result();

        Dealer dealer = new Dealer();
        Players players = createPlayers();

        blackjackGame.play(dealer, players);

        result.openResult(dealer, players);
        result.win(dealer, players);
    }

    private static Players createPlayers() {
        try {
            OutputView.printPlayerNameInstruction();
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }
}
