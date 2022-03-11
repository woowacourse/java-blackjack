package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.card.Card;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    void hit(Card card) {
        getCards().add(card);
        updateStatus();
    }

    public String getName() {
        return name.getValue();
    }
}
