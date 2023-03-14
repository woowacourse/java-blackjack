package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isDrawable() {
        return getState().isHit();
    }

    @Override
    public List<Card> getFirstCard() {
        return List.of(getAllCards().get(0));
    }
}
