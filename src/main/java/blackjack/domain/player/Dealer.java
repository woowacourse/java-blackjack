package blackjack.domain.player;

import blackjack.domain.card.PlayerCards;
import java.util.ArrayList;

public class Dealer extends AbstractPlayer implements Player {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int MAX_SCORE = 16;

    public Dealer() {
        this.name = DEALER_NAME;
        this.playerCards = new PlayerCards(new ArrayList<>());
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean canHit() {
        return getScore() <= MAX_SCORE;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name=" + name +
                ", playerCards=" + playerCards +
                '}';
    }
}
