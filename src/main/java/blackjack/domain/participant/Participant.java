package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Participant {
    private final Name name;
    private final CardHand cardHand;

    public Participant(final String name) {
        this.name = new Name(name);
        this.cardHand = new CardHand();
    }

    public void receiveCard(final Card card) {
        cardHand.receiveCard(card);
    }

    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
