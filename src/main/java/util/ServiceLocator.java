package util;

import controller.BlackjackController;
import view.OutputView;

public class ServiceLocator {
    private ServiceLocator() {}

    private static class Holder {
        private static final OutputView outputView = new OutputView();
        private static final BlackjackController blackjackController = new BlackjackController();
    }

    public static BlackjackController getBlackjackController() {
        return Holder.blackjackController;
    }

    public static OutputView getOutputView() {
        return Holder.outputView;
    }
}
