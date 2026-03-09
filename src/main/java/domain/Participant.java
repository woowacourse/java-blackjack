package domain;

import domain.player.Hand;
import domain.player.Name;
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

    public CardDto handInfo() {
        return hand.snapshot();
    }

    public boolean canReceive() {
        return !hand.isBust();
    }

    public String getName() {
        return name.value();
    }

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
