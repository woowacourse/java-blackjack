package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

import java.util.List;

public abstract class Gamer {
    public static final int DEAL_CARD_COUNT = 2;
    private static final int HIT_CARD_COUNT = 1;

    protected final Hand hand;

    protected Gamer() {
        this.hand = new Hand();
    }

    abstract boolean canContinue();

    abstract String getName();

    public void deal(Deck deck) {
        hand.addCards(deck.draw(DEAL_CARD_COUNT));
    }

    public void hit(Deck deck) {
        hand.addCards(deck.draw(HIT_CARD_COUNT));
    }

    public boolean isOnlyDeal() {
        return hand.getSize() == DEAL_CARD_COUNT;
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

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.totalScore();
    }
}
