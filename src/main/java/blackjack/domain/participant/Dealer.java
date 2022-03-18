package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int HIT_STANDARD = 17;

    public Dealer(List<Card> cards) {
        super(new Name("딜러"), cards);
    }

    @Override
    public List<Card> showInitialCards() {
        return List.of(getCards().get(0));
    }

    public boolean shouldHit() {
        return getScore().isLessThan(HIT_STANDARD);
    }
}
