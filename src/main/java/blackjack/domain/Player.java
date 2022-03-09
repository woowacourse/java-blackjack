package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private final String name;
    private final HoldingCard holdingCard;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.holdingCard = new HoldingCard(cards);
    }

    public boolean isFinished() {
        return holdingCard.isBust();
    }
}
