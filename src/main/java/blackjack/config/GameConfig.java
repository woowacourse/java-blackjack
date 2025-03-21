package blackjack.config;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameConfig {

    private final InputView inputView;
    private final OutputView outputView;

    public GameConfig(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

}
