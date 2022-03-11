package blackjack.controller;

import blackjack.domain.BlackJackMachine;
import blackjack.domain.CardDeck;
import blackjack.domain.Choice;
import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.ParticipantResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());
        List<String> names = InputView.getParticipantNames();
        Dealer dealer = new Dealer();
        Participants participants = new Participants(names);
        blackJackMachine.giveInitialCards(dealer, participants);

        OutputView.printInitialCards(dealer, participants);
        for (Participant participant : participants) {
            askAndGiveCardToParticipant(blackJackMachine, participant);
        }

        while (dealer.canAddCard()) {
            blackJackMachine.giveCardToDealer(dealer);
            OutputView.printDealerGetCardMessage(dealer);
        }

        OutputView.printTotalScore(dealer, dealer.getTotalScore());
        for (Participant participant : participants) {
            OutputView.printTotalScore(participant, participant.getTotalScore());
        }

        ParticipantResult results = new ParticipantResult(dealer, participants);
        DealerResult dealerResult = new DealerResult(results.getDealerResult());

        OutputView.printResults(dealer, dealerResult, results);
    }

    private void askAndGiveCardToParticipant(BlackJackMachine blackJackMachine, Participant participant) {
        Choice choice;
        do {
            choice = InputView.getChoice(participant);
            blackJackMachine.giveCardToParticipant(participant, choice);
            OutputView.printPlayerCards(participant);
        } while (choice == Choice.YES && participant.canAddCard());
    }
}
