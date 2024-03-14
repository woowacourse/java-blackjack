package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.LinkedHashMap;


public class BlackJack {

    private final Dealer dealer;
    private final Participants participants;

    public BlackJack(final Dealer dealer, final Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing() {
        dealer.receiveCard(dealer.draw(2));

        for (Participant participant : participants.getValue()) {
            participant.receiveCard(dealer.draw(2));
        }
    }

    public int dealerHit() {
        int count = 0;
        while (dealer.shouldHit()) {
            dealer.receiveCard(dealer.draw());
            count++;
        }
        return count;
    }

    public BlackJackResult saveParticipantResult() {
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        for (Participant participant : participants.getValue()) {
            result.put(participant, isWinner(participant));
        }
        return new BlackJackResult(result);
    }

    private WinStatus isWinner(Participant participant) {
        if (participant.isBust()) {
            return WinStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinStatus.WIN;
        }
        return isWinnerWhenNotBust(participant);
    }

    private WinStatus isWinnerWhenNotBust(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = dealer.getScore();

        if (dealer.isBlackJack() && participant.isBlackJack()) {
            return WinStatus.PUSH;
        }
        if (participantScore == dealerScore) {
            return WinStatus.PUSH;
        }
        if (participant.isBlackJack()) {
            return WinStatus.BLACKJACK;
        }
        return WinStatus.from(participantScore > dealerScore);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Participants getParticipants() {
        return participants;
    }
}
