package controller;

import domain.blackjack.BetAmount;
import domain.blackjack.BettingResult;
import domain.blackjack.BlackJack;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.inputParticipantName();
        Participants participants = new Participants(names);
        Map<Participant, BetAmount> betAmount = createBetAmount(participants);
        BlackJack blackJack = new BlackJack(participants);

        blackJack.beginDealing(this::beginBlackJack);
        blackJack.play(InputView::inputHitOption, OutputView::printParticipantHands);
        dealerHit(blackJack);

        printScore(participants);
        printResult(betAmount, participants.getDealer());
    }

    private LinkedHashMap<Participant, BetAmount> createBetAmount(Participants participants) {
        LinkedHashMap<Participant, BetAmount> betAmount = new LinkedHashMap<>();
        for (Participant participant : participants.getNotDealerParticipant()) {
            betAmount.put(participant, new BetAmount(InputView.inputBetAmount(participant.getName())));
        }
        return betAmount;
    }

    private void beginBlackJack(Participants participants) {
        OutputView.printBeginDealingInformation(participants);
        OutputView.printDealerHands(participants.getDealer());
        for (Participant participant : participants.getNotDealerParticipant()) {
            OutputView.printParticipantHands(participant);
        }
    }

    private void dealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScore(Participants participants) {
        OutputView.printParticipantResult(participants.getDealer());
        for (Participant participant : participants.getNotDealerParticipant()) {
            OutputView.printParticipantResult(participant);
        }
    }

    private void printResult(Map<Participant, BetAmount> bet, Dealer dealer) {
        BettingResult bettingResult = new BettingResult(bet, dealer);
        OutputView.printBlackJackResult(bettingResult);
    }
}
