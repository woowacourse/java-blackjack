package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;

    public Player(Name name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isHittable() {
        return calculateSum() < HIT_STANDARD;
    }
}
