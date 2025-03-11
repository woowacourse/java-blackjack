package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Player extends Participant {
    private final String name;

    public Player(String name, CardHand cardHand) {
        super(cardHand);
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return getScore().isUnderGoal();
    }

    @Override
    public List<Card> showStartCards() {
        List<Card> cards = cardHand.getCards();
        return cards.subList(0,2);
    }

    @Override
    public String getName() {
        return name;
    }
}
