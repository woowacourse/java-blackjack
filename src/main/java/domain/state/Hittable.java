package domain.state;

import domain.card.Card;
import domain.card.Cards;

public class Hittable extends Running {

    public static final int DEFAULT_USER_HITTABLE_THRESHOLD = 21;
    public static final int DEFAULT_DEALER_HITTABLE_THRESHOLD = 16;

    private final int hittableThreshold;

    public Hittable(Cards cards, int hittableThreshold) {
        super(cards);
        this.hittableThreshold = hittableThreshold;
    }

    public static Hittable initialUserState() {
        return new Hittable(Cards.emptyCards(), DEFAULT_USER_HITTABLE_THRESHOLD);
    }

    public static Hittable initialDealerState() {
        return new Hittable(Cards.emptyCards(), DEFAULT_DEALER_HITTABLE_THRESHOLD);
    }

    @Override
    public State hit(Card card) {
        Cards cards = cards();
        cards.addCard(card);
        if (isBlackjack(cards)) {
            return new Blackjack(cards);
        } else if (isBust(cards)) {
            return new Bust(cards);
        } else if (isBustThreshold(cards) || isOverHittableThreshold()) {
            return new Stay(cards);
        }
        return new Hittable(cards, this.hittableThreshold);
    }

    private boolean isBustThreshold(Cards cards) {
        return cards.computeOptimalSum() == Bust.BUST_THRESHOLD;
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }

    @Override
    public StateType type() {
        return StateType.HITTABLE;
    }

    private boolean isBlackjack(Cards cards) {
        return cards.size() == Blackjack.BLACKJACK_CARD_SIZE && cards.computeOptimalSum() == Blackjack.BLACKJACK_SUM;
    }

    private boolean isBust(Cards cards) {
        return cards.computeOptimalSum() > Bust.BUST_THRESHOLD;
    }

    private boolean isOverHittableThreshold() {
        return cards().computeOptimalSum() > this.hittableThreshold;
    }
}
