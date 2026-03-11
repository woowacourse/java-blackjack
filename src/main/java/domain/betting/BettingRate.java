package domain.betting;

public record BettingRate (
        double bettingRate
){
    public BettingRate reverseBettingRate() {
        return new BettingRate(bettingRate * -1);
    }
}
