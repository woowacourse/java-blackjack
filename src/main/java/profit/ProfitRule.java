package profit;

import domain.player.User;

import java.util.HashMap;
import java.util.Map;

public class ProfitRule {
    private static Map<Boolean, ProfitStrategy> profitStrategy;

    static {
        profitStrategy = new HashMap<>();
        profitStrategy.put(true,new BlackJackStrategy());
        profitStrategy.put(false,new NonBlackJackStrategy());
    }

    public static ProfitStrategy getProfitRule(User targetUser, User user){
        return profitStrategy.get(targetUser.isBlackJack()||user.isBlackJack());
    }
}
