package domain.participant;

import domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected final Hand hand;
    private final Name name;

    Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    abstract boolean isDrawable();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public abstract List<Card> initialHand();

    public List<Card> hand() {
        return hand.cards();
    }

    public String name() {
        return name.value();
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
}
