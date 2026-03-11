package domain.betting;

public record BettingRate (
        double bettingRate
){

    public Money payOut(Money money) {
        return money.changeMoney(this);
    }

}
