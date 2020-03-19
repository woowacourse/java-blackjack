package profit;

import domain.DecisionWinner;
import domain.player.User;

public class NonBlackJackStrategy implements ProfitStrategy {
    private static final double LOOSE_RATE = -1.0d;

    @Override
    public double calculateProfitStrategy(User targetUser, User user) {
        if (DecisionWinner.compareWinner(targetUser, user)) {
            return targetUser.getMoney();
        }
        return (LOOSE_RATE * targetUser.getMoney());
    }
}
