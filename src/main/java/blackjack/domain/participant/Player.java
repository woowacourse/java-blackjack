package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;

public class Player extends Participant {

    private final Name name;

    public Player(final Name name, final Money money) {
        super(money);
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
        this.state = this.state.receiveCard(card);
    }
}
