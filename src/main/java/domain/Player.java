package domain;

import java.util.List;
import java.util.Objects;

public class Player {

    protected static final int BLACK_JACK = 21;

    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    Player(List<Card> cards) {
        this.name = new Name("Test");
        this.hand = new Hand(cards);
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return getTotalScore() > BLACK_JACK;
    }

    public boolean isHittable() {
        return getTotalScore() < BLACK_JACK;
    }

    public int getTotalScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
