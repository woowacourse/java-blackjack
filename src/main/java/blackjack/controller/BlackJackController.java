package blackjack.controller;

import blackjack.configuration.ApplicationConfiguration;
import blackjack.domain.game.GameInputOutput;
import blackjack.domain.game.GameManager;
import blackjack.domain.user.Nickname;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public BlackJackController(ApplicationConfiguration configuration) {
        inputView = configuration.getInputView();
        outputView = configuration.getOutputView();
        gameManager = configuration.getGameManager();
        gameManager.setUpInputOutput(makeGameInputOutput());
    }

    public void run() {
        List<Nickname> nicknames = inputView.readNicknames();
        gameManager.runGame(nicknames);
    }

    private GameInputOutput makeGameInputOutput() {
        return new GameInputOutput(
                outputView::printInitialHands,
                inputView::readWannaHit,
                outputView::printHitResult,
                outputView::printDealerDrawing,
                outputView::printFinalHands,
                outputView::printGameResult);
    }
}
