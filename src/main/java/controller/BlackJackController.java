package controller;

import model.BlackJack;
import model.Participants;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String rawParticipants = "딜러,";
        rawParticipants += inputView.readParticipantNames();
        String[] parsedName = InputParser.parseName(rawParticipants);


        Participants participants = Participants.of(parsedName);
        participants.getDealer().setDealer(true);
        BlackJack blackJack = BlackJack.from(participants);

        blackJack.dealOut();

        outputView.printDealOut(participants, 1);
    }
}
