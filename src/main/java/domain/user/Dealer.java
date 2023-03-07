package domain.user;

import domain.Card.Card;
import java.util.List;

public class Dealer extends Participant {
    
    public Dealer() {
        super(DEALER_NAME);
    }
    
    @Override
    public List<Card> getReadyCards() {
        if (cards.size() < 1) {
            throw new IllegalStateException();
        }
        return List.of(cards.get(0));
    }
}
