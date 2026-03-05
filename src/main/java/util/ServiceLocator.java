package util;

import controller.BlackjackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class ServiceLocator {
    private ServiceLocator() {}

    private static class Holder {
        private static final OutputView outputView = new OutputView();
        private static final InputView inputView = new InputView();
        private static final BlackjackController blackjackController = new BlackjackController();
    }

    public static BlackjackController getBlackjackController() {
        return Holder.blackjackController;
    }

    public static OutputView getOutputView() {
        return Holder.outputView;
    }

    public static InputView getInputView() {
        return Holder.inputView;
    }
}
