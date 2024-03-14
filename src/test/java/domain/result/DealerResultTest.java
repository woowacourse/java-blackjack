package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Profit;
import domain.cards.Hand;
import domain.gamer.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerResultTest {

    @DisplayName("플레이어의 수익을 바탕으로 딜러의 수익을 계산한다.")
    @Test
    void calculateProfit() {
        Map<Player, Profit> playersProfit = new HashMap<>();
        playersProfit.put(new Player("p1", new Hand()), new Profit(10));
        playersProfit.put(new Player("p2", new Hand()), new Profit(-10));
        playersProfit.put(new Player("p3", new Hand()), new Profit(0));
        playersProfit.put(new Player("p4", new Hand()), new Profit(20));

        DealerResult dealerResult = new DealerResult();
        dealerResult.calculateProfit(playersProfit);

        assertThat(dealerResult.getProfit().getValue()).isEqualTo(-20);
    }
}