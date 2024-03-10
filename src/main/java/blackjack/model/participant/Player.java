package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;
import java.util.Objects;

public class Player {
    private static final int HITTABLE_THRESHOLD = 21;

    protected final Name name;
    protected Hand hand;

    Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(final String rawName, final Hand hand) {
        return new Player(new Name(rawName), hand);
    }

    public void receiveCard(final Card card) {
        this.hand = hand.addCard(card);
    }

    public int notifyScore() {
        return hand.calculateScore();
    }

    public List<Card> openCards() {
        return hand.getCards();
    }

    public boolean canHit() {
        return hand.calculateScore() < HITTABLE_THRESHOLD;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int announceCardCount() {
        return hand.countSize();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
