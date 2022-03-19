package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import java.util.List;
import java.util.Map;

public final class Dealer extends Human {
    private static final int HIT_DEALER_POINT = 16;

    public Dealer(final List<Card> cards) {
        super(new Cards(cards), new Name("딜러"));
    }

    public int getRemainMoney(final Map<Player, Integer> shouldPayMoneys) {
        return shouldPayMoneys.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public boolean isAbleToHit() {
        return getPoint() <= HIT_DEALER_POINT;
    }
}
