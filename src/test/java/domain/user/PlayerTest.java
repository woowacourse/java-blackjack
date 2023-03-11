package domain.user;

import domain.CardFixtures;
import domain.card.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    @Test
    @DisplayName("블랙잭이 아닌 경우 credit만큼 수익이 증가한다")
    void increaseRevenueWhenNotBlackjack() {
        CardPool cardPool = new CardPool(
                List.of(
                        CardFixtures.ofNumber(CardNumber.JACK),
                        CardFixtures.ofNumber(CardNumber.FIVE)
                )
        );
        Player player = new Player("test", cardPool, 2000);

        player.increaseRevenue();

        assertThat(player.getRevenue()).isEqualTo(2000);
    }

    @Test
    @DisplayName("블랙잭인 경우 credit * 1.5만큼 수익이 증가한다")
    void increaseRevenueWhenBlackjack() {
        CardPool cardPool = new CardPool(
                List.of(
                        CardFixtures.ofNumber(CardNumber.JACK),
                        CardFixtures.ofNumber(CardNumber.ACE)
                )
        );
        Player player = new Player("test", cardPool, 2000);

        player.increaseRevenue();

        assertThat(player.getRevenue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("credit만큼 수익이 감소한다")
    void decreaseRevenue() {
        Player player = new Player("test", new CardPool(Collections.emptyList()), 2000);

        player.decreaseRevenue();

        assertThat(player.getRevenue()).isEqualTo(-2000);
    }
}
