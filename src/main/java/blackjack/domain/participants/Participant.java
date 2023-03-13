package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_PARTICIPANT_NAME_EMPTY;

import blackjack.domain.card.Card;
import blackjack.dto.HandResult;
import blackjack.dto.HandStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    public Participant(final String name) {
        validateName(name);
        this.name = name;
        this.hand = new Hand(new ArrayList<>());
    }

    public Participant(final String name, final List<Card> cards) {
        validateName(name);
        this.name = name;
        this.hand = new Hand(cards);
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_PARTICIPANT_NAME_EMPTY);
        }
    }

    public void take(final Card card) {
        hand.add(card);
    }

    public int computeCardsScore() {
        return hand.sumScore();
    }

    public boolean isBust() {
        return hand.hasBustedScore();
    }

    public boolean isBlackJack() {
        return hand.hasBlackJackScore();
    }

    public boolean isNotBustNorHasMaxScore() {
        return hand.hasScoreUnderMax();
    }

    public String getName() {
        return name;
    }

    public List<Card> cards() {
        return hand.cards();
    }

    public HandResult toHandResult() {
        return new HandResult(getName(), cards(), computeCardsScore());
    }

    public abstract HandStatus toHandStatus();

    public abstract boolean isHitAble();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
