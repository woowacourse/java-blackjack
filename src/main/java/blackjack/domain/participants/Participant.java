package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;
import java.util.List;

abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean canHit();

    public final String getName() {
        return name.getValue();
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final void hitFrom(Deck deck) {
        hand.addCard(deck.draw());
    }

    public final Score getScore() {
        return hand.calculateScore();
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
