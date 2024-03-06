package blackjack.domain.participant;

import blackjack.domain.Card;
import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    private final Name name;

    Player(List<Card> cards, Name name) {
        super(cards);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(Collections.emptyList(), new Name(name));
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }

    public Name getName() {
        return name;
    }
}
