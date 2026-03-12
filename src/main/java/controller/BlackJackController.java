package controller;

import java.util.ArrayList;
import java.util.List;
import model.BlackJack;
import model.factory.ParticipantsFactory;
import model.participant.Participant;
import model.Participants;
import util.InputParser;
import util.Randoms;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int TARGET_NUMBER = 21;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String inputNames = inputView.readParticipantNames();
        String[] names = InputParser.parseName(inputNames);

        List<Integer> betAmounts = getBetAmounts(names);

        Participants participants = ParticipantsFactory.create(names, betAmounts);
        BlackJack blackJack = BlackJack.from(participants);

        blackJack.dealOut();
        outputView.printDealOut(participants);

        playersCardDraw(participants);

        Participant dealer = participants.getDealer();
        if (dealer.dealerNeedDraw()) {
            dealer.draw(Randoms.pick());
            outputView.printDealerDraw();
        }

        outputView.printHandsAndScore(participants);
        outputView.printResultRevenue(blackJack.calculateRevenue());
    }

    private List<Integer> getBetAmounts(String[] names) {
        List<Integer> betAmounts = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            String bettingAmount = inputView.readBettingAmount(names[i]);
            betAmounts.add(Integer.parseInt(bettingAmount));
        }
        return betAmounts;
    }

    private void playersCardDraw(Participants participants) {
        for (Participant participant : participants) {
            if (isDealerOrReachTargetNumber(participant)) {
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
    }

    private boolean isDealerOrReachTargetNumber(Participant participant) {
        if (participant.isDealer()) {
            return true;
        }

        if (participant.calculateScore() == TARGET_NUMBER) {
            return true;
        }
        return false;
    }
}
