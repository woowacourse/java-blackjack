package blackjack.domain.player;

import blackjack.domain.card.PlayerCards;
import java.util.ArrayList;

public class Participant extends AbstractPlayer implements Player {

    public Participant(Name name) {
        this.name = name;
        this.playerCards = new PlayerCards(new ArrayList<>());
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", playerCards=" + playerCards +
                '}';
    }
}
