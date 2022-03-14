package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public boolean isFinished() {
        return holdingCard.isBust();
    }
}
