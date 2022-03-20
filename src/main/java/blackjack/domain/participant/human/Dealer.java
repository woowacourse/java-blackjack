package blackjack.domain.participant.human;

import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import java.util.List;
import java.util.Map;

public final class Dealer extends Human {
    private static final int HIT_DEALER_POINT = 16;

    public Dealer(final List<Card> cards) {
        super(cards, new Name("딜러"));
        System.out.println("딜러 : " + state.getClass());
    }

    public int getRemainMoney(final Map<Player, Integer> shouldPayMoneys) {
        return - shouldPayMoneys.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Card getFirstCard() {
        return state.cards().getFirstCard();
    }

    public boolean isAbleToHit() {
        return getPoint() <= HIT_DEALER_POINT;
    }
}
