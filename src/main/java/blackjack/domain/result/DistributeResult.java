package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.List;

public class DistributeResult {

    private String name;
    private List<Card> cards;

    private int cardSum;

    public DistributeResult(Participant participant) {
        this.name = participant.getName();
        this.cards = List.copyOf(participant.getCards());
        this.cardSum = participant.getCardSum();
    }

    public int getCardSum() {
        return cardSum;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
