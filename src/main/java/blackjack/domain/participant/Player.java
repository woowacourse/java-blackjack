package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public void receiveCards(List<Card> cards) {
        cards.forEach(holdingCard::add);
    }

    @Override
    public boolean isFinished() {
        return holdingCard.isBust();
    }
}
