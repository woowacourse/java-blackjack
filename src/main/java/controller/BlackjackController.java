package controller;

import util.ServiceLocator;
import view.Message;
import view.OutputView;

public class BlackjackController {
    private final OutputView outputView;

    public BlackjackController() {
        this.outputView = ServiceLocator.getOutputView();
    }

    public void run() {
        outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
    }
}
