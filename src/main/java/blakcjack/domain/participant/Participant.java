package blakcjack.domain.participant;

import blakcjack.domain.Outcome;
import blakcjack.domain.card.Card;
import blakcjack.domain.name.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    public static final int ACE_ADDITIONAL_VALUE = 10;
    public static final int BLACKJACK_VALUE = 21;

    protected final Name name;
    protected final List<Card> cards = new ArrayList<>();

    protected Participant(final String name) {
        this.name = new Name(name);
    }

    public abstract boolean supports(ParticipantType participantType);

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = calculateMinimumPossibleScore();
        int aceCount = calculateAceCount();

        for (int i = 0; i < aceCount; i++) {
            score = getNextPossibleScore(score);
        }
        return score;
    }

    private int getNextPossibleScore(final int sum) {
        if (sum + ACE_ADDITIONAL_VALUE <= BLACKJACK_VALUE) {
            return sum + ACE_ADDITIONAL_VALUE;
        }
        return sum;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateMinimumPossibleScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();
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

    private Outcome judgeOutcomeByBust() {
        if (isBust()) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome judgeOutcomeByScore(final Participant other) {
        final int myScore = calculateScore();
        final int otherScore = other.calculateScore();

        if (myScore > otherScore) {
            return Outcome.WIN;
        }
        if (myScore < otherScore) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    private boolean isBust() {
        return BLACKJACK_VALUE < calculateScore();
    }

    public boolean isScoreLowerThanBlackJackValue() {
        return calculateScore() < BLACKJACK_VALUE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getNameValue() {
        return name.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(getCards(), that.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getCards());
    }
}
