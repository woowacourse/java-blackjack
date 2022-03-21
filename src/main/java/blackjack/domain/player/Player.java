package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Player {

    protected final Hand hand;

    protected Player(final Hand hand) {
        this.hand = hand;
    }

    public void receiveCard(final Card card) {
        hand.save(card);
    }

    public List<Card> showCards() {
        return List.copyOf(hand.getHand());
    }

    public int calculateResult() {
        return hand.calculateTotalPoint();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public abstract List<Card> openCards();

    public abstract boolean isReceivable();

}
