package domain.participant;

import domain.CanAddCardThreshold;
import domain.Cards;
import domain.card.Card;

public class Participant {
    private final Cards cards;

    public Participant(final CanAddCardThreshold canAddCardThreshold) {
        cards = new Cards(canAddCardThreshold);
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard() {
        return cards.canAddCard();
    }
}
