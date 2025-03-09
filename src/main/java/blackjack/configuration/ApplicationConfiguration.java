package blackjack.configuration;

import blackjack.domain.game.GameInputOutput;
import blackjack.domain.game.GameManager;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ApplicationConfiguration {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final GameInputOutput gameInputOutput = new GameInputOutput(
            outputView::printInitialHands,
            inputView::readWannaHit,
            outputView::printHitResult,
            outputView::printDealerDrawing,
            outputView::printFinalHands,
            outputView::printGameResult);
    private final GameManager gameManager = new GameManager();

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
