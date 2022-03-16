package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.strategy.hit.HitStrategy;

public abstract class User {

    protected final Hand hand;
    private final Name name;

    private User(Hand hand, Name name) {
        this.hand = hand;
        this.name = name;
    }

    protected User(String input) {
        this(new Hand(), new Name(input));
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isWinTo(User other) {
        return this.hand.isWinTo(other.hand);
    }

    public boolean hitOrStay(Deck deck, HitStrategy strategy) {
        boolean isHit = strategy.isHit();
        if (isHit) {
            receiveCard(deck.drawCard());
        }
        return isHit && !isBust();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public abstract List<Card> showInitCards();

}
