package blackjack.model;

import blackjack.exception.ErrorMessage;

public class Player extends Participant {
    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(String name, double money) {
        this.name = new Name(name);
        validateMoney(money);
        this.bettingAmount = new BettingAmount(money);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean canReceive() {
        return getScore().isPlayerHitScore();
    }

    private void validateMoney(double money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_NOT_ZERO.getMessage());
        }
    }
}
