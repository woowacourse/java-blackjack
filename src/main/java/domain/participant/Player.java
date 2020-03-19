package domain.participant;

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

    public double beatDealer(Dealer dealer) {
        if (this.isBlackJack() && dealer.isBlackJack()) {
            return 0;
        }
        if (this.isBlackJack()) {
            return bettingMoney.getAmount() * (1.5);
        }
        if (this.isBust() || isLowerThanDealerScore(dealer)) {
            return bettingMoney.getAmount() * (-1);
        }
        if (dealer.isBust() || isHigherThanDealerScore(dealer)) {
            return bettingMoney.getAmount();
        }
        return 0;
    }

    private boolean isLowerThanDealerScore(Dealer dealer) {
        return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
    }

    private boolean isHigherThanDealerScore(Dealer dealer) {
        return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() > dealer.calculateScore());
    }

}
