package blackjack.domain.player;

import static blackjack.domain.Rule.WINNING_SCORE;

public class Participant extends Player {

    private Money money;

    public Participant(final String name) {
        super(name);
    }

    public void betMoney(final Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean canTakeCard() {
        return getTotalScore() <= WINNING_SCORE.getValue();
    }
}
