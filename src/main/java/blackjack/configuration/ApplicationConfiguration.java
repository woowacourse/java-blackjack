package blackjack.configuration;

import blackjack.domain.CardManager;
import blackjack.domain.GameManager;
import blackjack.domain.GameUserStorage;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ApplicationConfiguration {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CardManager cardManager = new CardManager();
    private final GameUserStorage gameUserStorage = new GameUserStorage();
    private final GameManager gameManager = new GameManager(cardManager, gameUserStorage);

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
