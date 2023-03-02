package domain;

import domain.participant.Participant;
import java.util.List;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public List<Card> getReadyCards() {
        if (cards.size() < 1) {
            throw new IllegalStateException();
        }
        return List.of(cards.get(0));
    }
}
