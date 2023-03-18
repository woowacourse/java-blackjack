package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RewardsTest {

    @Test
    void calculate_rewards() {
        Dealer dealer = new Dealer();
        Player merry = new Player(new Name("merry"), 500);
        Player chaechae = new Player(new Name("chaechae"), 500);
        LinkedHashMap<User, Double> rewards = new LinkedHashMap<>();
        rewards.put(dealer, 5000.0);
        rewards.put(merry, 5000.0);
        rewards.put(chaechae, 5000.0);

        assertDoesNotThrow(() -> Rewards.of(rewards));
    }

    @Test
    void get_dealer_result() {
        Dealer dealer = new Dealer();
        LinkedHashMap<User, Double> rawRewards = new LinkedHashMap<>();
        rawRewards.put(dealer, 5000.0);
        Rewards rewards = Rewards.of(rawRewards);

        assertThat(rewards.removeAndGetDealerResult()).isEqualTo(5000);
    }
}