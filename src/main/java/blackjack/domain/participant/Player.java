package blackjack.domain.participant;

import blackjack.domain.hands.Hands;
import blackjack.domain.hands.Name;

public class Player extends Participant {
    private final Name name;
    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean canAddCard() {
        return (!super.isBust());
    }

    public String getName() {
        return name.getName();
    }
}
