package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Player(final Name name, final Money money) {
        super(name, money);
        validateName(name);
    }

    private void validateName(final Name name) {
        if (DEALER_NAME.equals(name.getValue())) {
            throw new IllegalArgumentException("이름으로 \"딜러\" 는 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore().isBust();
    }

    public void receiveCard(final Card card) {
        this.state = this.state.receiveCard(card);
    }
}
