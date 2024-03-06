import java.util.HashMap;
import java.util.Map;

public class BlackJack {
    private Deck deck;
    private Dealer dealer;
    private Participants participants;
    private Map<Player, Boolean> result;

    public BlackJack(Deck deck, Dealer dealer, Participants participants) {
        this.deck = deck;
        this.dealer = dealer;
        this.participants = participants;
        this.result = new HashMap<>();
    }

    public void beginDealing() {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        for (int i = 0; i < participants.getValue().size(); i++) {
            participants.getValue().get(i).receiveCard(deck.draw());
            participants.getValue().get(i).receiveCard(deck.draw());
        }
    }

    public boolean isWinner(Participant participant) {
        int participantScore = participant.calculateScore();
        int dealerScore = dealer.calculateScore();
        if (!participant.canHit()) {
            return false;
        }
        if (!dealer.canHit()) {
            return true;
        }

        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant);
        }
        return participantScore > dealerScore;
    }

    public void savePlayerResult() {
        for (Participant participant : participants.getValue()) {
            result.put(participant, isWinner(participant));
        }
    }

    public Map<Player, Boolean> getResult() {
        return result;
    }

    private boolean isWinnerByCardCount(Participant participant) {
        int participantCardCount = participant.getCardCount();
        int dealerCardCount = dealer.getCardCount();

        return participantCardCount < dealerCardCount;
    }
}
