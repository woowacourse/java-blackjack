package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.inputParticipantName();
        Participants participants = new Participants(names);
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(dealer, participants);

        startBlackJack(blackJack, participants, dealer);
        askHit(participants, dealer);
        dealerHit(dealer);

        printScore(dealer, participants);
        printResult(blackJack);
    }

    private void printResult(BlackJack blackJack) {
        BlackJackResult blackJackResult = blackJack.savePlayerResult();
        OutputView.printFinalResult(blackJackResult);
    }

    private void printScore(Dealer dealer, Participants participants) {
        OutputView.printPlayerScore(dealer);
        for (Participant participant : participants.getValue()) {
            OutputView.printPlayerScore(participant);
        }
    }

    private void dealerHit(Dealer dealer) {
        while (dealer.shouldHit()) {
            OutputView.printDealerHit();
            dealer.receiveCard(dealer.draw());
        }
    }

    private void askHit(Participants participants, Dealer dealer) {
        for (Participant participant : participants.getValue()) {
            hit(participant, dealer);
        }
    }

    private void startBlackJack(BlackJack blackJack, Participants participants, Dealer dealer) {
        blackJack.beginDealing();
        OutputView.printParticipants(participants);
        OutputView.printDealerCard(dealer);
        for (Participant participant : participants.getValue()) {
            OutputView.printCard(participant);
        }
    }

    private void hit(Participant participant, Dealer dealer) {
        while (participant.canHit() && "y".equals(InputView.inputDraw(participant.getName()))) {
            participant.receiveCard(dealer.draw());
            OutputView.printCard(participant);
        }
    }
}
