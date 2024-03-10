package domain.gamer;

import domain.card.Card;
import java.util.List;

public abstract class Gamer {
    private static final int BLACKJACK_SUM_COND = 21;
    private static final int BLACKJACK_CARD_COUNT_COND = 2;
    protected Name name;
    protected Hand hand;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public abstract boolean isStay();

    public void hit(final Card card) {
        hand.add(card);
    }

    public int calculateTotalScore() {
        return hand.sum();
    }

    public boolean isBust() {
        return hand.isOverBlackJack();
    }

    public boolean isBlackJack() {
        return hand.sum() == BLACKJACK_SUM_COND && hand.getCards().size() == BLACKJACK_CARD_COUNT_COND;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Name getName() {
        return name;
    }
}
