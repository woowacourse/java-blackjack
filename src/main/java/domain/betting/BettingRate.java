package domain.betting;

public enum BettingRate {

    BLACK_JACK_PUSH(1.0),
    BLACK_JACK(1.5),
    PLAYER_WIN(1.0),
    PLAYER_LOSE(-1.0),
    ;

    private final double bettingRate;

    BettingRate(double bettingRate) {
        this.bettingRate = bettingRate;
    }

    public double getBettingRate() {
        return bettingRate;
    }

}
