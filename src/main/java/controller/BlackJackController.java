package controller;

import domain.blackjack.BlackJack;
import domain.blackjack.BlackJackResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    private static final String HIT_OPTION = "y";

    public void run() {
        List<String> names = InputView.inputParticipantName();
        Participants participants = new Participants(names);
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(dealer, participants);


        blackJack.beginDealing(this::beginBlackJack);
        //askParticipantHit(participants, dealer);
        blackJack.play(this::participantHit);
        dealerHit(blackJack);

        printScore(dealer, participants);
        printResult(blackJack);
    }

    private void beginBlackJack(Participants participants, Dealer dealer) {
        OutputView.printBeginDealingInformation(participants);
        OutputView.printDealerHands(dealer);
        for (Participant participant : participants.getValue()) {
            OutputView.printParticipantHands(participant);
        }
    }

    private void askParticipantHit(Participants participants, Dealer dealer) {
        /*for (Participant participant : participants.getValue()) {
            participantHit(participant, dealer);
        }*/
    }

    private void participantHit(Participant participant, Dealer dealer) {
        while (isHitOption(participant)) {
            participant.receiveCard(dealer.draw());
            OutputView.printParticipantHands(participant);
        }
    }

    private boolean isHitOption(Participant participant) {
        return participant.canHit() && HIT_OPTION.equals(InputView.inputHitOption(participant.getName()));
    }

    private void dealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScore(Dealer dealer, Participants participants) {
        OutputView.printParticipantResult(dealer);
        for (Participant participant : participants.getValue()) {
            OutputView.printParticipantResult(participant);
        }
    }

    private void printResult(BlackJack blackJack) {
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        OutputView.printBlackJackResult(blackJackResult);
    }
}
