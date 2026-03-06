package controller;

import model.BlackJack;
import model.participant.Participant;
import model.Participants;
import util.InputParser;
import util.Randoms;
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
        BlackJack blackJack = BlackJack.from(participants);

        blackJack.dealOut();
        outputView.printDealOut(participants);
        blackJack.setFirstTurn();

        for (Participant participant : participants) {
            if (participant.getName().equals("딜러")) {
                continue;
            }

            if (participant.calculateScore() == 21) {
                continue;
            }

            String input;
            do {
                input = inputView.readHitOrNot(participant.getName());
                if (input.equals("n")) {
                    outputView.printHands(participant);
                    break;
                }
                participant.draw(Randoms.pick());
                if (participant.isBust()) {
                    outputView.printBustState(participant.getName(), participant.calculateScore());
                    outputView.printHands(participant);
                    break;
                }
                outputView.printHands(participant);

            } while (input.equals("y"));
        }

        Participant dealer = participants.getDealer();
        if (dealer.dealerNeedDraw()) {
            dealer.draw(Randoms.pick());
            outputView.printDealerDraw();
        }

        outputView.printHandsAndScore(participants);
        outputView.printResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResult());
    }
}
