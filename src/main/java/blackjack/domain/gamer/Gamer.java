package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

import java.util.List;

public abstract class Gamer {
    public static final int DEAL_CARD_COUNT = 2;

    protected final Hand hand;

    protected Gamer(Deck deck) {
        this.hand = new Hand(deck);
    }

    protected abstract boolean canContinue();

    protected abstract String getName();

    public void deal() {
        hand.draw(DEAL_CARD_COUNT);
    }

    public void hit() {
        hand.draw();
    }

    public boolean isOnlyDeal() {
        return hand.size() == DEAL_CARD_COUNT;
    }

    public boolean isBust() {
        return hand.isBustScore();
    }

    public boolean isBlackjack() {
        return isMaxScore() && isOnlyDeal();
    }

    public boolean isMaxScore() {
        return hand.isBlackjackScore();
    }

    public boolean isUnderBound() {
        return hand.isUnderBoundScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.totalScore();
    }
}
