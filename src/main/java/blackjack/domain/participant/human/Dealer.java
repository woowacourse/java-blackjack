package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Point;
import java.util.List;

public final class Dealer extends Human {
    private static final Point HIT_DEALER_POINT = Point.fromValue(16);

    public Dealer(List<Card> rawCards) {
        super(new Cards(rawCards), Name.valueOf("딜러"));
    }

    public boolean isAbleToHit() {
        return Point.fromCards(cards).compareTo(HIT_DEALER_POINT) <= 0;
    }
}
