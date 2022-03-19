package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import java.util.List;

public final class Dealer extends Human {
    private static final int HIT_DEALER_POINT = 16;

    public Dealer(List<Card> rawCards) {
        super(new Cards(rawCards), new Name("딜러"));
    }

    public boolean isAbleToHit() {
        return getPoint() <= HIT_DEALER_POINT;
    }
}
