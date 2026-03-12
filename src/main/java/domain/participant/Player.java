package domain.participant;

import domain.PlayerStatus;
import domain.constant.Result;

public class Player extends Participant {
    private final PlayerStatus status;

    public Player(String name, int bettingMoney) {
        super(name);
        this.status = new PlayerStatus(bettingMoney);
    }

    public boolean isNaturalBlackJack() {
        return status.isNaturalBlackJack();
    }

    public void markNaturalBlackJack() {
        status.markNaturalBlackJack();
    }

    public double calculateProceeds(Result result) {
        return status.calculateProceeds(result);
    }

    @Override
    public boolean canDraw() {
        return !(isBust() || isNaturalBlackJack());
    }
}