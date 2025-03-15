package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Shape shape;
    private final CardScore cardScore;

    public Card(final Shape shape, final CardScore cardScore) {
        this.shape = shape;
        this.cardScore = cardScore;
    }

    public boolean isAce() {
        return this.cardScore == CardScore.A;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Card card)) {
            return false;
        }
        return shape == card.shape && cardScore == card.cardScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, cardScore);
    }

    public Shape getShape() {
        return shape;
    }

    public String getCardScoreName() {
        return cardScore.getName();
    }

    public int getCardMinNumber() {
        return cardScore.getMinNumber();
    }

    public int getCardMaxNumber() {
        return cardScore.getMaxNumber();
    }
}
