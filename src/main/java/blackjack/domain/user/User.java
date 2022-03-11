package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.strategy.HitStrategy;

public abstract class User {

    protected final Hand hand;
    protected final Name name;

    protected User(Name name) {
        this.hand = new Hand();
        this.name = name;
    }

    protected User(String input) {
        this(new Name(input));
    }

    public void drawInitCards(Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public void drawCard(Deck deck) {
        hand.drawCard(deck);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isWinTo(User other) {
        return this.hand.isWinTo(other.hand);
    }

    public boolean hitOrStay(Deck deck, HitStrategy hitStrategy) {
        if (hitStrategy.isHit()) {
            drawCard(deck);
            return true;
        }
        return false;
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public Score getScore() {
        return hand.getScore();
    }

    public abstract List<Card> showInitCards();
}
