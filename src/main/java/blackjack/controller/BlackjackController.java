package blackjack.controller;

import blackjack.domain.BlackjackMachine;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.ParticipantResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        final BlackjackMachine blackJackMachine = new BlackjackMachine(new CardDeck());
        final Dealer dealer = new Dealer();
        final Participants participants = generateParticipants();

        giveInitialCardsToPlayer(blackJackMachine, dealer, participants);
        askAndGiveCardsToParticipants(blackJackMachine, participants);
        giveCardsToDealer(blackJackMachine, dealer);

        calculateTotalScore(dealer, participants);
        decideTotalResult(dealer, participants);
    }

    private Participants generateParticipants() {
        try {
            final List<String> names = InputView.getParticipantNames();
            return new Participants(names);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return generateParticipants();
        }
    }

    private void giveInitialCardsToPlayer(final BlackjackMachine blackJackMachine,
                                          final Dealer dealer,
                                          final Participants participants) {
        blackJackMachine.giveInitialCards(dealer, participants);
        OutputView.printInitialCards(dealer, participants);
    }

    private void askAndGiveCardsToParticipants(final BlackjackMachine blackJackMachine,
                                               final Participants participants) {
        for (Participant participant : participants) {
            askAndGiveCardToParticipant(blackJackMachine, participant);
        }
        OutputView.printNewLine();
    }

    private void askAndGiveCardToParticipant(final BlackjackMachine blackJackMachine,
                                             final Participant participant) {
        Choice choice;
        do {
            choice = getChoice(participant);
            blackJackMachine.giveCardToParticipant(participant, choice);
            OutputView.printPlayerCards(participant);
        } while (choice.isHit() && participant.canTakeCard());
    }

    private Choice getChoice(final Participant participant) {
        try {
            return InputView.getChoice(participant);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getChoice(participant);
        }
    }

    private void giveCardsToDealer(final BlackjackMachine blackJackMachine,
                                   final Dealer dealer) {
        while (dealer.canTakeCard()) {
            blackJackMachine.giveCardToDealer(dealer);
            OutputView.printDealerGetCardMessage(dealer);
        }
        OutputView.printNewLine();
    }

    private void calculateTotalScore(final Dealer dealer,
                                     final Participants participants) {
        OutputView.printTotalScore(dealer, dealer.getTotalScore());
        for (Participant participant : participants) {
            OutputView.printTotalScore(participant, participant.getTotalScore());
        }
    }

    private void decideTotalResult(final Dealer dealer,
                                   final Participants participants) {
        final ParticipantResult participantResult = new ParticipantResult(dealer, participants);
        final DealerResult dealerResult = new DealerResult(participantResult);
        OutputView.printTotalResult(dealer, dealerResult, participantResult);
    }
}
