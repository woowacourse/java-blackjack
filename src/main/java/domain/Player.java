package domain;

import java.util.List;
import java.util.Objects;

public class Player {
    private static final int BUST_CONDITION = 21;

    private final Name name;
    private final Hand hand;

    public Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Player(final Name name) {
        this(name, new Hand());
    }

    public void dealCards(final List<Card> cards) {
        hand.addAll(cards);
    }

    public void dealCard(final Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.score();
    }

    public boolean isBust() {
        return score() > BUST_CONDITION;
    }

    public boolean isNotBust() {
        return score() <= BUST_CONDITION;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return hand.cards();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
