package controller;

import model.BlackJack;
import model.participant.Participant;
import model.Participants;
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
        Participants participants = Participants.of(inputView.readParticipantNames());
        BlackJack blackJack = BlackJack.from(participants, null);

        blackJack.dealOut();
        outputView.printDealOut(participants);
        blackJack.setFirstTurn();

        for (Participant participant : participants) {
            if (participant.isDealer()) {
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
