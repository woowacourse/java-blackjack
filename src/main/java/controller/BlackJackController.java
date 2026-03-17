package controller;

import java.util.Arrays;
import java.util.List;
import model.BlackJack;
import model.factory.ParticipantsFactory;
import model.participant.Participant;
import model.Participants;
import model.vo.BetAmount;
import util.InputParser;
import util.RandomNumberPicker;
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
        String inputNames = inputView.readParticipantNames();
        String[] names = InputParser.parseName(inputNames);

        List<BetAmount> betAmounts = getBetAmounts(names);

        Participants participants = ParticipantsFactory.create(names, betAmounts);
        BlackJack blackJack = BlackJack.from(participants);

        blackJack.dealOut();
        outputView.printDealOut(participants);

        playersCardDraw(participants);
        dealerNeedDraw(participants);

        outputView.printHandsAndScore(participants);
        outputView.printResultRevenue(blackJack.calculateRevenue());
    }

    private void dealerNeedDraw(Participants participants) {
        Participant dealer = participants.getDealer();
        if (dealer.dealerNeedDraw()) {
            dealer.draw(RandomNumberPicker.pick());
            outputView.printDealerDraw();
        }
    }

    private List<BetAmount> getBetAmounts(String[] names) {
        return Arrays.stream(names)
                .map(inputView::readBettingAmount)
                .map(Integer::parseInt)
                .map(BetAmount::of)
                .toList();
    }

    private void playersCardDraw(Participants participants) {
        for (Participant participant : participants) {
            String input;
            do {
                input = inputView.readHitOrNot(participant.getName());
                if (input.equals("n")) {
                    outputView.printHands(participant);
                    break;
                }
                participant.draw(RandomNumberPicker.pick());
                if (participant.isBust()) {
                    outputView.printBustState(participant.getName(), participant.calculateScore());
                    outputView.printHands(participant);
                    break;
                }
                outputView.printHands(participant);

            } while (input.equals("y"));
        }
    }
}
