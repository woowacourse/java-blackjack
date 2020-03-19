package domain.participant;

import domain.result.Result;

public class Player extends Participant {
    private Money bettingMoney;

    public Player(String name) {
        super(name);
        validateName(name);
    }

    public void setBettingMoney(Money bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 blank 값이 될 수 없습니다.");
        }
    }

    public Result beatDealer(Dealer dealer) {
        if (this.isBust() || isLowerThanDealerScore(dealer)) {
            return Result.LOSE;
        }
        if (dealer.isBust() || isHigherThanDealerScore(dealer)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private boolean isLowerThanDealerScore(Dealer dealer) {
        return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
    }

    private boolean isHigherThanDealerScore(Dealer dealer) {
        return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() > dealer.calculateScore());
    }



}
