package blackjack.domain.player;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.card.Hand;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, HandGenerator handGenerator) {
        this.name = name;
        this.hand = handGenerator.generate();
    }

    public String getName() {
        return name.getValue();
    }
}
