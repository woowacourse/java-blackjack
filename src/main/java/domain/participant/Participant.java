package domain.participant;

import static domain.game.BlackJackGame.BLACKJACK_CARD_SIZE;
import static domain.game.BlackJackGame.BLACKJACK_SCORE;

import controller.dto.response.ParticipantHandStatus;
import domain.card.Card;
import domain.game.DecisionToContinue;
import domain.game.Scoreboard;
import domain.game.deck.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Scoreboard scoreboard;

    protected Participant(final String name) {
        this.scoreboard = new Scoreboard(name);
    }

    public List<Card> pickCard(final Deck deck, final int count) {
        for (int index = 0; index < count; index++) {
            scoreboard.add(deck.pick());
        }
        return scoreboard.showCards();
    }

    public boolean isBlackJack() {
        return scoreboard.cardSize() == BLACKJACK_CARD_SIZE
                && scoreboard.resultScore() == BLACKJACK_SCORE;
    }

    public boolean isBusted() {
        return scoreboard.score() > BLACKJACK_SCORE;
    }

    public boolean isNotBusted() {
        return !isBusted();
    }

    public boolean isNotSameScoreAs(final Participant other) {
        return other.doesNotEqualResultScore(scoreboard.resultScore());
    }

    private boolean doesNotEqualResultScore(final int score) {
        return scoreboard.resultScore() != score;
    }

    public boolean hasMoreScoreThan(final Participant other) {
        return other.hasLessScore(scoreboard.resultScore());
    }

    private boolean hasLessScore(final int score) {
        return scoreboard.resultScore() < score;
    }

    public boolean hasLessOrSameCardThan(final Participant other) {
        return other.hasMoreOrSameCardSize(scoreboard.cardSize());
    }

    private boolean hasMoreOrSameCardSize(final int size) {
        return scoreboard.cardSize() >= size;
    }

    public ParticipantHandStatus createHandStatus() {
        return scoreboard.generateParticipantHandStatus();
    }

    public int cardSize() {
        return scoreboard.cardSize();
    }

    public String name() {
        return scoreboard.showName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Participant that = (Participant) o;
        return Objects.equals(scoreboard, that.scoreboard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreboard);
    }

    public abstract boolean canPickCard(final DecisionToContinue decision);

    public abstract ParticipantHandStatus createInitialHandStatus();
}
