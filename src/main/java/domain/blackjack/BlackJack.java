package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.LinkedHashMap;
import java.util.Map;


public class BlackJack {

    public static final int BEGIN_DRAW_COUNT = 2;

    private final Dealer dealer;
    private final Participants participants;

    public BlackJack(final Dealer dealer, final Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing() {
        dealer.receiveCard(dealer.draw(BEGIN_DRAW_COUNT));

        for (Participant participant : participants.getValue()) {
            participant.receiveCard(dealer.draw(BEGIN_DRAW_COUNT));
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

    public Map<Participant, WinStatus> makeResult() {
        Map<Participant, WinStatus> result = new LinkedHashMap<>();
        for (Participant participant : participants.getValue()) {
            result.put(participant, getWinStatusByParticipant(participant));
        }
        return result;
    }

    private WinStatus getWinStatusByParticipant(Participant participant) {
        if (participant.isBust()) {
            return WinStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinStatus.WIN;
        }
        return getWinStatusWhenNotBust(participant);
    }

    private WinStatus getWinStatusWhenNotBust(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = dealer.getScore();

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
