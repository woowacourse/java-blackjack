package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class HandResult {

    private final HandStatus handStatus;
    private final int sum;

    public HandResult(final String participantName, final List<Card> cards, final int sum) {
        this.handStatus = new HandStatus(participantName, cards);
        this.sum = sum;
    }

    public String getParticipantName() {
        return handStatus.getParticipantName();
    }

    public List<Card> getCards() {
        return handStatus.getCards();
    }

    public int getSum() {
        return sum;
    }
}
