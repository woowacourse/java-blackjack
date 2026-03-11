package domain.participant;

import domain.card.Card;
import domain.card.CardsSnapshot;
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

    public CardsSnapshot handInfo() {
        return hand.snapshot();
    }

    public CardsSnapshot firstCardInfo() {
        return hand.firstCardSnapshot();
    }

    public String getName() {
        return name.value();
    }

    public int getScore() {
        return hand.calculateScore();
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
