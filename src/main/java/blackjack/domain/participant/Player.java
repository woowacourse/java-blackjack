package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.Status;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    void hit(Card card) {
        cards.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (cards.getStatus() == Status.BUST) {
            super.status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public String getName() {
        return name.getValue();
    }
}
