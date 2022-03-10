package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;

    public Player(Name name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isHittable() {
        return Rule.INSTANCE.calculateSum(getCards()) < HIT_STANDARD;
    }
}
