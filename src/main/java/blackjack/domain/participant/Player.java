package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getValue();
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore().isBust();
    }

    public void receiveCard(final Card card) {
        validateState();
        this.hand.addCard(card);
        this.state = state.check(this.hand);
    }
}
