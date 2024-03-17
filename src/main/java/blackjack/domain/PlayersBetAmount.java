package blackjack.domain;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersBetAmount {

    private final Map<UserName, BetAmount> playersBetAmount;

    public PlayersBetAmount(Map<UserName, BetAmount> playersBetAmount) {
        this.playersBetAmount = playersBetAmount;
    }

    public Map<UserName, BetRevenue> calculateBetRevenue(Map<UserName, BetLeverage> playersBetLeverage) {
        Map<UserName, BetRevenue> playersBetRevenue = new LinkedHashMap<>();

        playersBetAmount.forEach((name, betAmount) -> playersBetRevenue.put(name,
                playersBetLeverage.get(name).applyLeverage(betAmount)));

        return playersBetRevenue;
    }
}
