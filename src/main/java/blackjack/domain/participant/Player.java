package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public List<Card> getInitialCards() {
        return hand.getCards();
    }

    public void bet(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.register(bettingAmount);
    }
}
