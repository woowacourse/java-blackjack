package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final Name name;

    public Player(String name, Hand hand) {
        super(hand);
        this.name = new Name(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return hand.getCards();
    }

    @Override
    public String getName() {
        return this.name.getValue();
    }

    public void bet(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.register(bettingAmount);
    }
}
