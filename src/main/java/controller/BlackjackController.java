package controller;

import java.util.List;
import service.BlackjackService;
import util.Parser;
import util.ServiceLocator;
import view.InputView;
import view.Message;
import view.OutputView;

public class BlackjackController {
    private final OutputView outputView;
    private final InputView inputView;
    private final Parser parser;
    private final BlackjackService blackjackService;

    public BlackjackController() {
        this.outputView = ServiceLocator.getOutputView();
        this.inputView = ServiceLocator.getInputView();
        this.parser = ServiceLocator.getParser();
        this.blackjackService = ServiceLocator.getBlackjackService();
    }

    public void run() {
        readParticipants();
        blackjackService.makeDeck();
        blackjackService.dealCards();
    }

    public void readParticipants() {
        while(true) {
            try {
                outputView.printMessage(Message.INPUT_PARTICIPANTS_MESSAGE);
                String participantsName = inputView.readParticipantsName();
                List<String> parsedParticipantsName = parser.parseParticipantsName(participantsName);
                blackjackService.saveParticipants(parsedParticipantsName);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }

    }
}
