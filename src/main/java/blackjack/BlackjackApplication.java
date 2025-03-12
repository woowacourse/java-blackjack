package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.Controller;
import blackjack.controller.ExceptionMessagePrintControllerProxy;
import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.reader.SystemReader;
import blackjack.view.writer.SystemWriter;

public class BlackjackApplication {

    public static void main(String[] args) {
        getController().run();
    }

    private static Controller getController() {
        final SystemWriter writer = new SystemWriter();
        final Controller controller = new BlackjackController(
                new InputView(writer, new SystemReader()),
                new OutputView(writer),
                new BlackjackService()
        );
        return new ExceptionMessagePrintControllerProxy(controller, writer);
    }
}
