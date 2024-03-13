package blackjack.model.participant;

import blackjack.model.deck.Card;
import blackjack.model.state.Hit;
import blackjack.model.state.State;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {
    private static final int HITTABLE_THRESHOLD = 21;

    private final String name;
    private State state;

    public Player(final String name) {
        validateNullAndEmptyName(name);
        this.name = name.trim();
        this.state = new Hit();
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() < HITTABLE_THRESHOLD;
    }

    public void receiveInitialCards(final List<Card> cards) {
        for (Card card : cards) {
            state = state.draw(card);
        }
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
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
