package domain.game;

import controller.dto.response.ParticipantHandStatus;
import domain.card.Card;
import domain.participant.Hand;
import java.util.List;
import java.util.Objects;

public class Scoreboard {
    private final String name;
    private final Hand hand;

    public Scoreboard(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void add(final Card card) {
        hand.saveCard(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public int resultScore() {
        return hand.calculateResultScore();
    }

    public int cardSize() {
        return hand.size();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public String showName() {
        return name;
    }

    public ParticipantHandStatus generateParticipantHandStatus() {
        return new ParticipantHandStatus(name, hand);
    }

    public ParticipantHandStatus generateParticipantHandStatus(final List<Card> cards) {
        return new ParticipantHandStatus(name, new Hand(cards));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Scoreboard that = (Scoreboard) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
