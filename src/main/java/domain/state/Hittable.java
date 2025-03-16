package domain.state;

import domain.card.Card;
import domain.card.Cards;

public class Hittable extends Running {

    public static final int DEFAULT_USER_HIT_THRESHOLD = 21;
    public static final int DEFAULT_DEALER_HIT_THRESHOLD = 16;

    private final int hittableThreshold;


    public Hittable(Cards cards, int hittableThreshold) {
        super(cards);
        this.hittableThreshold = hittableThreshold;
    }

    public static Hittable initialUserState() {
        return new Hittable(Cards.emptyCards(), DEFAULT_USER_HIT_THRESHOLD);
    }

    public static Hittable initialDealerState() {
        return new Hittable(Cards.emptyCards(), DEFAULT_DEALER_HIT_THRESHOLD);
    }

    @Override
    public State hit(Card card) {
        Cards cards = cards();
        cards.addCard(card);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        } else if (cards.isBust()) {
            return new Bust(cards);
        } else if (cards.isBustThreshold() || cards.computeOptimalSum() > this.hittableThreshold) {
            return new Stay(cards);
        }
        return new Hittable(cards, this.hittableThreshold);
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }
}
