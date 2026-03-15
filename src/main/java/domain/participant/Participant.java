package domain.participant;

import static constant.GameRule.BLACKJACK_CRITERION;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final Hand hand = new Hand();

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public boolean isBlackjack() {
        return hand.getSize() == 2 && hand.calculateScore() == BLACKJACK_CRITERION;
    }
}
