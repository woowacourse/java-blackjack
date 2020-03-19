package profit;

import domain.player.User;

public interface ProfitStrategy {
    double calculateProfitStrategy(User targetUser, User user);
}
