package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;
import java.util.Objects;

public class Player {
    private static final int HITTABLE_THRESHOLD = 21;

    protected final String name;
    protected final Hand hand;

    public Player(final String name, final Hand hand) {
        validateNullAndEmptyName(name);
        this.name = name.trim();
        this.hand = hand;
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    public void receiveCard(final Card card) {
        hand.addCard(card);
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

    public boolean hasManyCardsThan(Player other) {
        return hand.hasManyThan(other.hand);
    }

    public boolean hasSameCardsSizeThan(Player other) {
        return hand.hasSameSizeWith(other.hand);
    }

    public String getName() {
        return name;
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
