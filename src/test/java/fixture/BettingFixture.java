package fixture;

import blackjack.gamer.Betting;
import java.math.BigDecimal;

public class BettingFixture {
    public static Betting createBetting(int bettingAmount) {
        return new Betting(BigDecimal.valueOf(bettingAmount));
    }
}
