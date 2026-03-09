package util;

import domain.BlackjackGame;
import view.InputView;
import view.OutputView;

public class ServiceLocator {
    private ServiceLocator() {}

    private static class Holder {
        private static final OutputView outputView = new OutputView();
        private static final InputView inputView = new InputView();
        private static final Parser parser = new Parser();
        private static final BlackjackGame BLACKJACK_GAME = new BlackjackGame();
        private static final Validator validator = new Validator();
    }

    public static OutputView getOutputView() {
        return Holder.outputView;
    }

    public static InputView getInputView() {
        return Holder.inputView;
    }

    public static Parser getParser() {
        return Holder.parser;
    }

    public static BlackjackGame getBlackjackService() {
        return Holder.BLACKJACK_GAME;
    }

    public static Validator getValidator() {
        return Holder.validator;
    }
}
