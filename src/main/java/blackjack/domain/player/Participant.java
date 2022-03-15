package blackjack.domain.player;

import blackjack.domain.card.PlayerCards;
import java.util.ArrayList;

public class Participant extends AbstractPlayer implements Player {

    private static final int MAX_SCORE = 21;

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
        return getScore() <= MAX_SCORE;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", playerCards=" + playerCards +
                '}';
    }
}
