package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Player implements Playable {
    private final Name name;
    private Hand hand;

    Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(final String rawName, final Hand hand) {
        return new Player(new Name(rawName), hand);
    }

    @Override
    public void receiveCard(final Card card) {
        this.hand = hand.addCard(card);
    }

    @Override
    public List<Card> openCards() {
        return hand.getCards();
    }

    @Override
    public int getScore() {
        return hand.calculateScore();
    }

    @Override
    public int getCardCount() {
        return hand.countSize();
    }

    @Override
    public boolean canHit() {
        return hand.isUnder21();
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public Name getName() {
        return name;
    }
}
