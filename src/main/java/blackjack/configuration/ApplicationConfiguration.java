package blackjack.configuration;

import blackjack.domain.game.GameManager;
import blackjack.domain.user.PlayerStorage;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ApplicationConfiguration {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PlayerStorage playerStorage = new PlayerStorage();
    private final GameManager gameManager = new GameManager(playerStorage);

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
