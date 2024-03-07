package domain;

import java.util.HashMap;
import java.util.Map;

public class BlackJack {
    private Dealer dealer;
    private Participants participants;

    public BlackJack(Dealer dealer, Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing() {
        dealer.receiveCard(dealer.draw());
        dealer.receiveCard(dealer.draw());
        for (int i = 0; i < participants.getValue().size(); i++) {
            participants.getValue().get(i).receiveCard(dealer.draw());
            participants.getValue().get(i).receiveCard(dealer.draw());
        }
    }

    public boolean isWinner(Participant participant) {
        if (!participant.canHit()) {
            return false;
        }
        if (!dealer.canHit()) {
            return true;
        }
        return isNotBustWinner(participant);
    }

    private boolean isNotBustWinner(Participant participant) {
        int participantScore = participant.getScore();
        int dealerScore = dealer.getScore();
        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant);
        }
        return participantScore > dealerScore;
    }

    public BlackJackResult savePlayerResult() {
        Map<Participant, Boolean> result = new HashMap<>();
        for (Participant participant : participants.getValue()) {
            result.put(participant, isWinner(participant));
        }
        return new BlackJackResult(result);
    }

    private boolean isWinnerByCardCount(Participant participant) {
        int participantCardCount = participant.getCardCount();
        int dealerCardCount = dealer.getCardCount();

        return participantCardCount < dealerCardCount;
    }
}
