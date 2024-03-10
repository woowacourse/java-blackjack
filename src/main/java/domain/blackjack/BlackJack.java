package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class BlackJack {

    private final Dealer dealer;
    private final Participants participants;

    public BlackJack(final Dealer dealer, final Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing(BiConsumer<Participants, Dealer> beginBlackJack) {
        dealer.receiveCard(dealer.draw());
        dealer.receiveCard(dealer.draw());
        participants.beginDealing(dealer);

        beginBlackJack.accept(participants, dealer);
    }

    public void play(BiConsumer<Participant, Dealer> participantConsumer) {
        participants.participantHit(participantConsumer, dealer);
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

    WinStatus isWinner(Participant participant) {
        if (!participant.canHit()) {
            return WinStatus.from(false);
        }
        if (!dealer.canHit()) {
            return WinStatus.from(true);
        }
        return isWinnerWhenNotBust(participant);
    }

    private WinStatus isWinnerWhenNotBust(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = dealer.getScore();
        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant);
        }
        return WinStatus.from(participantScore > dealerScore);
    }

    private WinStatus isWinnerByCardCount(Participant participant) {
        int participantCardCount = participant.getCardCount();
        int dealerCardCount = dealer.getCardCount();

        return WinStatus.from(participantCardCount < dealerCardCount);
    }
}
