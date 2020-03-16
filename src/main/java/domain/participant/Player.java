package domain.participant;

import domain.result.Result;

public class Player extends Participant {

    public Player(String name) {
        super(name);
        validateName(name);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 blank 값이 될 수 없습니다.");
        }
    }

    public Result beatDealer(Dealer dealer) {
        if (this.isBust() || isLowerThanDealerScore(dealer)) {
            return Result.패;
        }
        if (dealer.isBust() || !isLowerThanDealerScore(dealer)) {
            return Result.승;
        }
        return Result.무;
    }

    private boolean isLowerThanDealerScore(Dealer dealer) {
        return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
    }

}
