package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final CardHand cardHand;

    public Participant(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }

    public String getName() {
        return name;
    }
}
