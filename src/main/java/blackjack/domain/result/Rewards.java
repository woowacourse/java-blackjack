package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.LinkedHashMap;

public class Rewards {

    private final LinkedHashMap<User, Double> rewards;

    private Rewards(LinkedHashMap<User, Double> rewards) {
        this.rewards = rewards;
    }

    public static Rewards of(LinkedHashMap<User, Double> rewards) {
        return new Rewards(rewards);
    }

    public LinkedHashMap<User, Double> getRewards() {
        return rewards;
    }

    public Double removeDealerResult() {
        Dealer dealer = (Dealer) rewards.keySet().stream()
                .filter(User::isDealer)
                .findFirst()
                .get();
        return rewards.remove(dealer);
    }
}
