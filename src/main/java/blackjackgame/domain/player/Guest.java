package blackjackgame.domain.player;

import java.util.List;
import java.util.Objects;

import blackjackgame.domain.card.Card;

public class Guest extends Player {
    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;

    public Guest(final Name name, final List<Card> cards) {
        super(cards);
        this.name = name;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_MAX_SCORE;
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest)o;
        return Objects.equals(name, guest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
