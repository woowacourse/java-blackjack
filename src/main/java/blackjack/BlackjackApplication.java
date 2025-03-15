package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.util.RetryHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.reader.SystemReader;
import blackjack.view.writer.SystemWriter;

public final class BlackjackApplication {
    
    public static void main(String[] args) {
        getController().run();
    }
    
    private static BlackjackController getController() {
        final SystemWriter writer = new SystemWriter();
        final OutputView outputView = new OutputView(writer);
        return new BlackjackController(
                new InputView(writer, new SystemReader()),
                outputView,
                new RetryHandler(outputView)
        );
    }
}
