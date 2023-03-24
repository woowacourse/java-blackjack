package blackjack.domain.participants.status;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.running.Hit;
import blackjack.domain.participants.status.stopped.Blackjack;

import java.util.List;

public abstract class Started implements Status {

    protected final Hand cards;

    protected Started(final Hand cards) {
        this.cards = cards;
    }

    public Status initCards(final Card card1, final Card card2) {
        final Hand initCards = new Hand(card1, card2);
        if(initCards.isBlackjack()) {
            return new Blackjack(initCards);
        }
        return new Hit(initCards);
    }

    @Override
    public Score score() {
        return cards.getScore();
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

}
