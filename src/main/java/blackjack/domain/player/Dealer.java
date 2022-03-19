package blackjack.domain.player;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public class Dealer extends Participant {

    private static final Name NAME = new Name("딜러");
    private static final int DRAWABLE_BOUND = 16;

    public Dealer(HoldCards holdCards) {
        super(NAME, holdCards);
    }

    private Card findFirstCard() {
        return holdCards.getFirstCard()
            .orElseThrow(() -> new IllegalArgumentException("카드가 존재하지 않습니다."));
    }

    @Override
    public boolean canHit() {
        return getTotalNumber() <= DRAWABLE_BOUND;
    }

    @Override
    public List<Card> openCards() {
        return Collections.singletonList(findFirstCard());
    }

    @Override
    public Participant copy() {
        return new Dealer(holdCards.copy());
    }
}
