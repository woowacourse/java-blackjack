package fixture;

import domain.gamer.Betting;

public class BettingFixture {
    public static Betting createBetting(Integer bettingAmount) {
        return new Betting(bettingAmount);
    }
}
