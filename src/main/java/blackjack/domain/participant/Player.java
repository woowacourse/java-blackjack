package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.card.Card;

public class Player extends Participant {

    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    void hit(Card card) {
        getCards().add(card);
        updateStatus();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
