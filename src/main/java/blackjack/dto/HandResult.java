package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class HandResult {


    private final String participantName;
    private final List<Card> cards;
    private final int sum;

    public HandResult(final String participantName, final List<Card> cards, final int sum) {
        this.participantName = participantName;
        this.cards = cards;
        this.sum = sum;
    }

    public String getParticipantName() {
        return participantName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSum() {
        return sum;
    }
}
