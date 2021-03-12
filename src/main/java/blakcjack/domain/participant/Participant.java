package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.Cards;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.score.Score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Name name;
    protected final Cards cards;

    protected Participant(final String name) {
        this.name = new Name(name);
        this.cards = new Cards(Collections.emptyList());
    }

    public abstract boolean supports(ParticipantType participantType);

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public Outcome decideOutcome(final Participant other) {
        if (hasAnyBust(other)) {
            return judgeOutcomeByBust();
        }
        return judgeOutcomeByScore(other);
    }

    private boolean hasAnyBust(final Participant other) {
        return isBust() || other.isBust();
    }

    // TODO : 위치 이동 고려중
    private Outcome judgeOutcomeByBust() {
        if (isBust()) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome judgeOutcomeByScore(final Participant other) {
        final Score myScore = cards.calculateScore();
        final Score otherScore = other.cards.calculateScore();
        if (myScore.isHigherThan(otherScore)) {
            return Outcome.WIN;
        }

        if (myScore.isLowerThan(otherScore)) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    private boolean isBust() {
        return cards.isBust();
    }

    public boolean canDrawMoreCard() {
        return cards.isLowerThanBlackJack();
    }

    public List<Card> showCardList() {
        return cards.toList();
    }

    public String getNameValue() {
        return name.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
