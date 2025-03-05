package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.reader.SystemReader;
import blackjack.view.writer.SystemWriter;

public class BlackjackApplication {
    
    public static void main(String[] args) {
        getController().run();
    }
    
    private static BlackjackController getController() {
        final SystemWriter writer = new SystemWriter();
        return new BlackjackController(
                new InputView(writer, new SystemReader()),
                new OutputView(writer)
        );
    }
}
