package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.result.Point;
import java.util.List;

public final class Dealer extends Human {
    private static final int HIT_STANDARD_NUMBER = 16;

    public Dealer(List<Card> rawCards) {
        super(new Cards(rawCards), "딜러");
    }

    public boolean isAbleToHit() {
        return Point.fromCards(cards).get() <= HIT_STANDARD_NUMBER;
    }
}
