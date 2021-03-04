package blackjack.domain.participant;

import static blackjack.domain.game.BlackJackInitializer.THRESHOLD;

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

    public abstract int getCardSum();

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return getCardSum() > THRESHOLD;
    }
}
