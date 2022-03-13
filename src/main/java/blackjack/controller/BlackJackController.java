package blackjack.controller;

import blackjack.domain.BlackJackMachine;
import blackjack.domain.card.CardDeck;
import blackjack.domain.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.DealerResult;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.result.ParticipantResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        final BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());
        final Dealer dealer = new Dealer();
        final Participants participants = getParticipants();

        giveInitialCardsToPlayer(blackJackMachine, dealer, participants);
        askAndGiveCardsToParticipants(blackJackMachine, participants);
        giveCardsToDealer(blackJackMachine, dealer);

        calculateTotalScores(dealer, participants);
        decideResults(dealer, participants);
    }

    private Participants getParticipants() {
        try {
            final List<String> names = InputView.getParticipantNames();
            return new Participants(names);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getParticipants();
        }
    }

    private void giveInitialCardsToPlayer(final BlackJackMachine blackJackMachine, final Dealer dealer,
                                          final Participants participants) {
        blackJackMachine.giveInitialCards(dealer, participants);
        OutputView.printInitialCards(dealer, participants);
    }

    private void askAndGiveCardsToParticipants(final BlackJackMachine blackJackMachine, final Participants participants) {
        for (Participant participant : participants) {
            askAndGiveCardToParticipant(blackJackMachine, participant);
        }
        OutputView.printNewLine();
    }

    private void askAndGiveCardToParticipant(final BlackJackMachine blackJackMachine, final Participant participant) {
        Choice choice;
        do {
            choice = getChoice(participant);
            blackJackMachine.giveCardToParticipant(participant, choice);
            OutputView.printPlayerCards(participant);
        } while (choice == Choice.YES && participant.canAddCard());
    }

    private Choice getChoice(final Participant participant) {
        try {
            return InputView.getChoice(participant);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getChoice(participant);
        }
    }

    private void giveCardsToDealer(final BlackJackMachine blackJackMachine, final Dealer dealer) {
        while (dealer.canAddCard()) {
            blackJackMachine.giveCardToDealer(dealer);
            OutputView.printDealerGetCardMessage(dealer);
        }
        OutputView.printNewLine();
    }

    private void calculateTotalScores(Dealer dealer, Participants participants) {
        OutputView.printTotalScore(dealer, dealer.getTotalScore());
        for (Participant participant : participants) {
            OutputView.printTotalScore(participant, participant.getTotalScore());
        }
    }

    private void decideResults(final Dealer dealer, final Participants participants) {
        final ParticipantResult participantResult = new ParticipantResult(dealer, participants);
        final DealerResult dealerResult = new DealerResult(participantResult.getDealerResult());
        OutputView.printResults(dealer, dealerResult, participantResult);
    }
}
