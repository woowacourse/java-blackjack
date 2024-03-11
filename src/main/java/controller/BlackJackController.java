package controller;

import domain.blackjack.BlackJack;
import domain.blackjack.BlackJackResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import view.HitOption;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.inputParticipantName();
        Participants participants = new Participants(names);
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(dealer, participants);

        blackJack.beginDealing();
        printParticipantsCards(blackJack);

        blackJack.startParticipantHit(this::participantHit);
        dealerHit(blackJack);

        printScore(blackJack);
        printResult(blackJack);
    }

    private void printParticipantsCards(BlackJack blackJack) {
        Participants participants = blackJack.getParticipants();
        OutputView.printBeginDealingInformation(participants);
        OutputView.printDealerHands(blackJack.getDealer());
        for (Participant participant : participants.getValue()) {
            OutputView.printParticipantHands(participant);
        }
    }

    private void participantHit(Participant participant, Dealer dealer) {
        String option = InputView.inputHitOption(participant.getName());
        while (participant.canHit() && HitOption.isHitOption(option)) {
            participant.receiveCard(dealer.draw());
            OutputView.printParticipantHands(participant);
        }
    }

    private void dealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScore(BlackJack blackJack) {
        OutputView.printParticipantResult(blackJack.getDealer());
        Participants participants = blackJack.getParticipants();
        for (Participant participant : participants.getValue()) {
            OutputView.printParticipantResult(participant);
        }
    }

    private void printResult(BlackJack blackJack) {
        BlackJackResult blackJackResult = blackJack.saveParticipantResult();
        OutputView.printBlackJackResult(blackJackResult);
    }
}
