package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final static String DealerName = "딜러";
    private BlackJackGame blackJackGame;

    public void run() {
        final List<String> playerNames = enrollPlayerNames();
        initializeGame(playerNames);
        startGame(playerNames);
        hitOrStayForAvailablePlayers(playerNames);
        hitUntilDealerAvailable();
        endGame();
    }

    private List<String> enrollPlayerNames() {
        return InputHandler.retryForIllegalArgument(InputView::askPlayerNames, this::initializeGame,
                OutputView::showInputErrorMessage);
    }

    private void initializeGame(final List<String> playerNames) {
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), DealerName, playerNames);
    }

    private void startGame(final List<String> playerNames) {
        blackJackGame.handOut();
        OutputView.showOpenCards(DealerName, playerNames, blackJackGame.openHandStatuses());
    }

    private void hitOrStayForAvailablePlayers(final List<String> playerNames) {
        playerNames.forEach(this::repeatHitOrStayUntilPlayerWants);
    }

    private void repeatHitOrStayUntilPlayerWants(final String playerName) {
        HitCommand hitCommand = refreshHitCommand(playerName);
        while (hitCommand == HitCommand.YES) {
            blackJackGame.hitByName(playerName);
            OutputView.showPlayerCard(playerName, blackJackGame.openCardsByName(playerName));
            hitCommand = refreshHitCommand(playerName);
        }
    }

    private HitCommand refreshHitCommand(final String playerName) {
        if (blackJackGame.isAvailable(playerName)) {
            return InputHandler.retryForIllegalArgument(playerName, InputView::askToHit,
                    OutputView::showInputErrorMessage);
        }
        return HitCommand.NO;
    }

    private void hitUntilDealerAvailable() {
        final int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(DealerName, hitCount);
    }

    private void endGame() {
        OutputView.showHandStatuses(blackJackGame.openHandResults());
        OutputView.showTotalGameResult(blackJackGame.computeTotalGameResult());
    }
}
