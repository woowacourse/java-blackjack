package domain.participant;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(Name name, List<Card> hand) {
        super(name, hand);
    }

    @Override
    public boolean isNeedToDraw() {
        return !isBlackJack() && !isUpperBoundScore() && !isBust();
    }

    public boolean isNameMatch(Name name) {
        return this.getName().equals(name);
    }
}
