package blakjack;

import blakjack.domain.BlackjackGame;
import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.view.InputView;
import blakjack.view.OutputView;

import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<PlayerName> playerNames = catchPlayerNameException();
        final List<Chip> chips = catchChipException(playerNames);
        final BlackjackGame blackjackGame = new BlackjackGame(playerNames, chips);
        run(blackjackGame);
    }

    private static List<PlayerName> catchPlayerNameException() {
        try {
            return InputView.inputPlayerNames();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return catchPlayerNameException();
        }
    }

    private static List<Chip> catchChipException(final List<PlayerName> playerNames) {
        try {
            return InputView.inputBettingMoney(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return catchChipException(playerNames);
        }
    }

    private static void run(final BlackjackGame blackjackGame) {
        initCards(blackjackGame);
        hit(blackjackGame);
        showScore(blackjackGame);
        showProfit(blackjackGame);
    }

    private static void initCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCards();

        OutputView.printInitCards(blackjackGame);
    }

    private static void hit(final BlackjackGame blackjackGame) {
        for (final String name : blackjackGame.getPlayerNames()) {
            catchPlayerHitException(name, blackjackGame);
        }
        catchDealerHitException(blackjackGame);
    }

    private static void catchPlayerHitException(final String playerName, final BlackjackGame blackjackGame) {
        try {
            hitPlayer(playerName, blackjackGame);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void hitPlayer(final String name, final BlackjackGame blackjackGame) {
        while (InputView.inputHitRequest(name)) {
            blackjackGame.hitPlayer(name);
            OutputView.printCards(blackjackGame.findPlayerByName(name));
        }
        blackjackGame.stay(name);
    }

    private static void catchDealerHitException(final BlackjackGame blackjackGame) {
        try {
            blackjackGame.hitDealer();
            OutputView.printDealerHit();
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void showScore(final BlackjackGame blackjackGame) {
        OutputView.printScore(blackjackGame);
    }

    private static void showProfit(final BlackjackGame blackjackGame) {
        OutputView.printProfit(blackjackGame);
    }
}
