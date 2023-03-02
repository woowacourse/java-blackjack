package controller;

import domain.participant.Participants;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = new Participants(inputView.readPlayerNames());
        outputView.printInitialMessage(participants.getNames());
        outputView.printInitialState(participants);
    }
}
