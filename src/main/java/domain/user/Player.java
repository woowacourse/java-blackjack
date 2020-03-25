package domain.user;

import domain.BettingMoney;

import static domain.card.HandCard.PERFECT_SCORE;
import static domain.util.NullValidator.validateNull;

public class Player extends BlackjackUser {
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        super(name);

        validateNull(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= PERFECT_SCORE;
    }

    public int getBettingMoney() {
        return bettingMoney.get();
    }
}
