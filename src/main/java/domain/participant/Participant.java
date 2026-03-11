package domain.participant;

import domain.Score;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    protected Participant(Name name) {
        this.hand = new Hand();
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return getScore().isBust();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public String getName() {
        return name.value();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public abstract boolean canReceive();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant participant = (Participant) o;
        return Objects.equals(name, participant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
