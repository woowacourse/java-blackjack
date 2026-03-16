package domain.result;

import domain.player.User;
import java.math.BigDecimal;

public record RoundBetInfo(int round, User user, BigDecimal betAmount) {

    public UserProfit toUserProfit(GameResult gameResult, BigDecimal profit) {
        return new UserProfit(this, gameResult, profit);
    }
}
