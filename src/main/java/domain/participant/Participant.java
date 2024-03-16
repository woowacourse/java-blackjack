package domain.participant;

import static domain.game.BlackJackGame.BLACKJACK_CARD_SIZE;
import static domain.game.BlackJackGame.BLACKJACK_SCORE;

import controller.dto.response.ParticipantHandStatus;
import domain.card.Card;
import domain.game.DecisionToContinue;
import domain.game.deck.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected final String name;
    protected final Hand hand;

    protected Participant(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> pickCard(final Deck deck, final int count) {
        for (int index = 0; index < count; index++) {
            hand.saveCard(deck.pick());
        }
        return hand.getCards();
    }

    public boolean isBlackJack() {
        return hand.size() == BLACKJACK_CARD_SIZE && hand.calculateResultScore() == BLACKJACK_SCORE;
    }

    public boolean isBusted() {
        return hand.calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isNotBusted() {
        return !isBusted();
    }

    protected int calculateScore() {
        return hand.calculateScore();
    }

    private int calculateResultScore() {
        return hand.calculateResultScore();
    }

    public ParticipantHandStatus createHandStatus() {
        return new ParticipantHandStatus(name, hand);
    }

    public boolean isNotSameScoreAs(final Participant other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final Participant other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public boolean hasLessOrSameCardThan(final Participant other) {
        return getCardSize() <= other.getCardSize();
    }

    public int getCardSize() {
        return hand.size();
    }

    public String getName() {
        return this.name;
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
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract boolean canPickCard(final DecisionToContinue decision);

    public abstract ParticipantHandStatus createInitialHandStatus();
}
